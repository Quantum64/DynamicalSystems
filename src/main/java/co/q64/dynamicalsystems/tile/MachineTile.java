package co.q64.dynamicalsystems.tile;

import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.grid.energy.EnergyTiers;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.gui.MachineContainerFactory;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineGuiLayoutCache;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.recipe.Recipe;
import co.q64.dynamicalsystems.recipe.Recipes;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.tile.type.MachineTileType;
import co.q64.dynamicalsystems.util.NBTUtil;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoFactory
public class MachineTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private static final boolean USE_THREADED_MATCHER = true;
    private static final boolean USE_THREADED_CALCULATOR = true;

    private static final Direction[] DIRECTION_CACHE = Direction.values();

    private @Getter int inputSlots, outputSlots;
    private @Getter LazyOptional<MachineItemHandler> itemHandler = LazyOptional.of(this::createItemHandler);
    private @Getter Machine machine;
    private @Getter Voltage voltage;
    private Map<Direction, MachineSideConfiguration> sideConfigurations = new HashMap<>();
    private Map<Direction, LazyOptional<SidedMachineItemHandler>> delegateItemHandlers = new HashMap<>();
    private MachineContainerFactory containerFactory;
    private MachineGuiLayoutCache cache;
    private Recipes recipes;
    private boolean recalculateRecipe = false;
    private Recipe currentRecipe = null;
    private int processingTick, maxTicks;
    private EnergyTiers energyTiers;
    private NBTUtil nbtUtil;

    public MachineTile(@Provided co.q64.dynamicalsystems.gui.MachineContainerFactory containerFactory,
                       @Provided MachineTileType type, @Provided MachineGuiLayoutCache cache, @Provided Recipes recipes, @Provided EnergyTiers energyTiers, @Provided NBTUtil nbtUtil) {
        super(type);
        this.cache = cache;
        this.containerFactory = containerFactory;
        this.recipes = recipes;
        this.energyTiers = energyTiers;
        this.nbtUtil = nbtUtil;
    }

    public MachineTile(@Provided co.q64.dynamicalsystems.gui.MachineContainerFactory containerFactory,
                       @Provided MachineTileType type, @Provided MachineGuiLayoutCache cache, @Provided Recipes recipes, @Provided EnergyTiers energyTiers, @Provided NBTUtil nbtUtil,
                       MachineBlock block) {
        this(containerFactory, type, cache, recipes, energyTiers, nbtUtil);
        this.machine = block.getMachine();
        this.voltage = block.getVoltage();
        this.inputSlots = cache.get(machine.getRecipeType()).getInputSlots();
        this.outputSlots = cache.get(machine.getRecipeType()).getOutputSlots();
        sideConfigurations.put(Direction.UP, MachineSideConfiguration.INPUT);
        sideConfigurations.put(Direction.DOWN, MachineSideConfiguration.OUTPUT);
        markDirty();
    }

    private MachineItemHandler createItemHandler() {
        return new MachineItemHandler();
    }

    @Override
    public void read(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> {
            if (tag.contains("inventory")) {
                handler.deserializeNBT(tag.getCompound("inventory"));
            }
        });
        this.machine = nbtUtil.deserializeMachine(tag.getString("machine"));
        this.voltage = nbtUtil.deserializeVoltage(tag.getString("voltage"));
        this.inputSlots = cache.get(machine.getRecipeType()).getInputSlots();
        this.outputSlots = cache.get(machine.getRecipeType()).getOutputSlots();
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> tag.put("inventory", handler.serializeNBT()));
        tag.putString("machine", nbtUtil.serializeMachine(machine));
        tag.putString("voltage", nbtUtil.serializeVoltage(voltage));
        return super.write(tag);
    }

    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        tag.putInt("inputSlots", inputSlots);
        tag.putInt("outputSlots", outputSlots);
        tag.putString("machine", nbtUtil.serializeMachine(machine));
        tag.putString("voltage", nbtUtil.serializeVoltage(voltage));
        return tag;
    }

    public void handleUpdateTag(CompoundNBT tag) {
        this.inputSlots = tag.getInt("inputSlots");
        this.outputSlots = tag.getInt("outputSlots");
        this.machine = nbtUtil.deserializeMachine(tag.getString("machine"));
        this.voltage = nbtUtil.deserializeVoltage(tag.getString("voltage"));
        super.handleUpdateTag(tag);
    }

    private void readConfigurationTag(CompoundNBT tag) {

    }

    private void writeConfigurationTag(CompoundNBT tag) {

    }

    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
                .withInitial(MachineProperties.get(getBlockState().get(MachineProperties.FACING)), MachineSideConfiguration.FRONT)
                .withInitial(MachineProperties.UP, MachineSideConfiguration.INPUT)
                .withInitial(MachineProperties.DOWN, MachineSideConfiguration.OUTPUT)
                .withInitial(MachineProperties.RUNNING, currentRecipe != null)
                .build();
    }

    @Override
    public void tick() {
        if (machine == null) {
            System.out.println("MACHINE NULL on tick! (client: " + world.isRemote + ")");
            return;
        }
        if (recalculateRecipe) {
            recalculateRecipe = false;
            itemHandler.ifPresent(itemHandler -> {
                if (USE_THREADED_CALCULATOR) {
                    if (currentRecipe != null) {
                        if (currentRecipe.canProcess(itemHandler.getStacks(), inputSlots)) {
                            return;
                        }
                    }
                    currentRecipe = null;
                    requestModelDataUpdate();
                    recipes.get(machine.getRecipeType()).parallelStream().filter(recipe -> recipe.canProcess(itemHandler.getStacks(), inputSlots)).findFirst().ifPresent(r -> {
                        currentRecipe = r;
                        requestModelDataUpdate();
                    });
                    maxTicks = energyTiers.getProcessingTicks(voltage); //TODO power multiplier
                } else {
                    //TODO sequential matcher
                }
            });
        }
        if (currentRecipe != null) {
            if (processingTick < maxTicks) {
                processingTick++;
                return;
            }
            processingTick = 0;
            itemHandler.ifPresent(itemHandler -> {
                if (!currentRecipe.canProcess(itemHandler.getStacks(), inputSlots)) {
                    currentRecipe = null;
                    recalculateRecipe = true;
                    requestModelDataUpdate();
                    return;
                }
                currentRecipe.process(itemHandler.getStacks(), inputSlots, (slot, item) -> {
                    itemHandler.setStackInSlot(slot, item);
                });
            });
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Machine");
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return containerFactory.create(windowId, playerInventory, this);
    }

    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction direction) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (direction != null) {
                return (LazyOptional<T>) delegateItemHandlers.computeIfAbsent(direction, dir -> LazyOptional.of(() ->
                        new SidedMachineItemHandler(itemHandler.orElse(null), dir)
                ));
            }
            return (LazyOptional<T>) itemHandler;
        }
        return super.getCapability(cap);
    }


    public class MachineItemHandler extends ItemStackHandler {
        protected MachineItemHandler() {
            super(inputSlots + outputSlots);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack result = super.extractItem(slot, amount, simulate);
            if (currentRecipe == null) {
                recalculateRecipe = true;
            }
            return result;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (machine == null) {
                System.out.println("MACHINE NULL on insert item! (client: " + world.isRemote + ")");
                return ItemStack.EMPTY;
            }
            if (slot >= inputSlots) {
                return stack;
            }
            List<ItemStack> simulatedInputs = new ArrayList<>(inputSlots + 1);
            for (int i = 0; i < inputSlots; i++) {
                simulatedInputs.add(stacks.get(i));
            }
            simulatedInputs.add(stack);
            if (USE_THREADED_MATCHER) {
                if (!recipes.get(machine.getRecipeType()).parallelStream().anyMatch(recipe -> recipe.matches(simulatedInputs, voltage, simulatedInputs.size()))) {
                    return stack;
                }
            } else {
                boolean matched = false;
                for (Recipe recipe : recipes.get(machine.getRecipeType())) {
                    if (recipe.matches(simulatedInputs, voltage, simulatedInputs.size())) {
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    return stack;
                }
            }
            ItemStack result = super.insertItem(slot, stack, simulate);
            if (currentRecipe == null) {
                recalculateRecipe = true;
            }
            return result;
        }

        public List<ItemStack> getStacks() {
            return stacks;
        }

        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }
    }

    public class SidedMachineItemHandler implements IItemHandlerModifiable {
        private MachineItemHandler delegate;
        private Direction side;

        protected SidedMachineItemHandler(MachineItemHandler delegate, Direction side) {
            this.delegate = delegate;
            this.side = side;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot < inputSlots) {
                return ItemStack.EMPTY;
            }
            return delegate.extractItem(slot, amount, simulate);
        }

        @Override
        public void setStackInSlot(int slot, ItemStack stack) {
            delegate.setStackInSlot(slot, stack);
        }

        @Override
        public int getSlots() {
            return delegate.getSlots();
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return delegate.getStackInSlot(slot);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return delegate.insertItem(slot, stack, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return delegate.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return delegate.isItemValid(slot, stack);
        }
    }
}
