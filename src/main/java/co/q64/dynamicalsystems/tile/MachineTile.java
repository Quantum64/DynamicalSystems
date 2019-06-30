package co.q64.dynamicalsystems.tile;

import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.gui.MachineContainerFactory;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineGuiLayoutCache;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.recipe.Recipe;
import co.q64.dynamicalsystems.recipe.Recipes;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.tile.type.MachineTileType;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

@AutoFactory
public class MachineTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private static final boolean USE_THREADED_MATCHER = true;
    private static final boolean USE_THREADED_CALCULATOR = true;

    private MachineContainerFactory containerFactory;
    private @Getter int inputSlots, outputSlots;
    private @Getter LazyOptional<MachineItemHandler> itemHandler = LazyOptional.of(this::createItemHandler);
    private @Getter Machine machine;
    private @Getter Voltage voltage;
    private MachineGuiLayoutCache cache;
    private Recipes recipes;
    private boolean recalculateRecipe = false;
    private Recipe currentRecipe = null;

    public MachineTile(@Provided co.q64.dynamicalsystems.gui.MachineContainerFactory containerFactory,
                       @Provided MachineTileType type, @Provided MachineGuiLayoutCache cache, @Provided Recipes recipes) {
        super(type);
        this.cache = cache;
        this.containerFactory = containerFactory;
        this.recipes = recipes;
    }

    private MachineItemHandler createItemHandler() {
        return new MachineItemHandler();
    }

    @Override
    public void read(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> handler.deserializeNBT(tag.getCompound("inventory")));
        super.read(tag);
        init();
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> tag.put("inventory", handler.serializeNBT()));
        return super.write(tag);
    }

    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
                .withInitial(MachineProperties.get(getBlockState().get(MachineProperties.FACING)), MachineSideConfiguration.FRONT)
                .withInitial(MachineProperties.UP, MachineSideConfiguration.INPUT)
                .withInitial(MachineProperties.DOWN, MachineSideConfiguration.OUTPUT)
                .withInitial(MachineProperties.RUNNING, false)
                .build();
    }

    @Override
    public void tick() {
        if (world == null) {
            return;
        }
        if (recalculateRecipe) {
            recalculateRecipe = false;
            itemHandler.ifPresent(itemHandler -> {
                if (USE_THREADED_CALCULATOR) {
                    recipes.get(machine.getRecipeType()).parallelStream().filter(recipe -> recipe.canProcess(itemHandler.getStacks(), inputSlots)).findAny().ifPresent(r -> currentRecipe = r);
                } else {
                    //TODO sequential matcher
                }
            });
        }
        if (currentRecipe != null) {
            itemHandler.ifPresent(itemHandler -> {
                if (!currentRecipe.canProcess(itemHandler.getStacks(), inputSlots)) {
                    currentRecipe = null;
                    recalculateRecipe = true;
                    return;
                }
                currentRecipe.process(itemHandler.getStacks(), inputSlots, (slot, item) -> {
                    itemHandler.setStackInSlot(slot, item);
                });
            });
        }
    }

    @Override
    public void setPos(BlockPos p_174878_1_) {
        super.setPos(p_174878_1_);
        init();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Machine");
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return containerFactory.create(windowId, playerInventory, this);
    }

    public <T> LazyOptional<T> getCapability(Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (LazyOptional<T>) itemHandler;
        }
        return super.getCapability(cap);
    }

    private void init() {
        if (machine != null) {
            return;
        }
        this.machine = ((MachineBlock) getBlockState().getBlock()).getMachine();
        this.voltage = ((MachineBlock) getBlockState().getBlock()).getVoltage();
        this.inputSlots = cache.get(machine.getRecipeType()).getInputSlots();
        this.outputSlots = cache.get(machine.getRecipeType()).getOutputSlots();
        requestModelDataUpdate();
    }

    public class MachineItemHandler extends ItemStackHandler {
        protected MachineItemHandler() {
            super(inputSlots + outputSlots);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot < inputSlots) {
                return ItemStack.EMPTY;
            }
            return playerExtractItem(slot, amount, simulate);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return playerInsertItem(slot, stack, simulate);
        }

        public ItemStack playerExtractItem(int slot, int amount, boolean simulate) {
            ItemStack result = super.extractItem(slot, amount, simulate);
            if (currentRecipe == null) {
                recalculateRecipe = true;
            }
            return result;
        }

        public ItemStack playerInsertItem(int slot, ItemStack stack, boolean simulate) {
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
    }
}
