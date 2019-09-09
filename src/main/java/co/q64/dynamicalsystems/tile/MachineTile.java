package co.q64.dynamicalsystems.tile;

import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.grid.energy.EnergyTiers;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.gui.MachineContainerFactory;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineGuiLayoutCache;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.net.PacketManager;
import co.q64.dynamicalsystems.recipe.Recipe;
import co.q64.dynamicalsystems.recipe.Recipes;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.util.NBTUtil;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MachineTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private static final boolean USE_THREADED_MATCHER = true;
    private static final boolean USE_THREADED_CALCULATOR = true;

    private static final Direction[] DIRECTION_CACHE = Direction.values();

    protected @Inject MachineContainerFactory containerFactory;
    protected @Inject MachineGuiLayoutCache cache;
    protected @Inject Recipes recipes;
    protected @Inject EnergyTiers energyTiers;
    protected @Inject NBTUtil nbtUtil;
    protected @Inject PacketManager packetManager;

    private @Getter int inputSlots, outputSlots;
    private @Getter LazyOptional<MachineItemHandler> itemHandler = LazyOptional.of(this::createItemHandler);
    private @Getter Machine machine;
    private @Getter Voltage voltage;
    private Map<Direction, MachineSideConfiguration> sideConfigurations = new HashMap<>();
    private Map<Direction, LazyOptional<SidedMachineItemHandler>> delegateItemHandlers = new HashMap<>();
    private boolean recalculateRecipe = false;
    private boolean running = false;
    private Recipe currentRecipe = null;
    private int processingTick, maxTicks;
    private IIntArray tracked = new IIntArray() {
        public int get(int index) {
            if (index == 0) {
                return processingTick;
            } else {
                return maxTicks;
            }
        }

        public void set(int index, int value) {
            if (index == 0) {
                processingTick = value;
            } else {
                maxTicks = value;
            }
        }

        public int size() {
            return 2;
        }
    };

    @Inject
    protected MachineTile(TileEntityType<MachineTile> type) {
        super(type);
    }

    public MachineTile setup(MachineBlock block) {
        this.machine = block.getMachine();
        this.voltage = block.getVoltage();
        this.inputSlots = cache.get(machine.getRecipeType()).getInputSlots();
        this.outputSlots = cache.get(machine.getRecipeType()).getOutputSlots();
        sideConfigurations.put(Direction.UP, MachineSideConfiguration.INPUT);
        sideConfigurations.put(Direction.DOWN, MachineSideConfiguration.OUTPUT);
        markDirty();
        return this;
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
        readShared(tag);
        this.inputSlots = cache.get(machine.getRecipeType()).getInputSlots();
        this.outputSlots = cache.get(machine.getRecipeType()).getOutputSlots();
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> tag.put("inventory", handler.serializeNBT()));
        writeShared(tag);
        return super.write(tag);
    }

    public void handleUpdateTag(CompoundNBT tag) {
        this.inputSlots = tag.getInt("inputSlots");
        this.outputSlots = tag.getInt("outputSlots");
        readShared(tag);
        if (world.isRemote) {
            updateModel();
        }
        super.handleUpdateTag(tag);
    }

    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        tag.putInt("inputSlots", inputSlots);
        tag.putInt("outputSlots", outputSlots);
        writeShared(tag);
        return tag;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tag = new CompoundNBT();
        writeShared(tag);
        return new SUpdateTileEntityPacket(getPos(), 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        CompoundNBT tag = packet.getNbtCompound();
        readShared(tag);
        updateModel();
    }

    public void writeShared(CompoundNBT tag) {
        tag.putString("machine", nbtUtil.serializeMachine(machine));
        tag.putString("voltage", nbtUtil.serializeVoltage(voltage));
        tag.putBoolean("running", running());
        writeConfigurationTag(tag);
    }

    public void readShared(CompoundNBT tag) {
        this.machine = nbtUtil.deserializeMachine(tag.getString("machine"));
        this.voltage = nbtUtil.deserializeVoltage(tag.getString("voltage"));
        this.running = tag.getBoolean("running");
        readConfigurationTag(tag);
    }

    private void readConfigurationTag(CompoundNBT parent) {
        if (parent.contains("sides")) {
            CompoundNBT tag = parent.getCompound("sides");
            for (Direction direction : DIRECTION_CACHE) {
                if (tag.contains(direction.name())) {
                    getSideConfigurations().put(direction, MachineSideConfiguration.valueOf(tag.getString(direction.name())));
                }
            }
        }
    }

    private void writeConfigurationTag(CompoundNBT parent) {
        CompoundNBT tag = new CompoundNBT();
        for (Direction direction : DIRECTION_CACHE) {
            tag.putString(direction.name(), getSideConfigurations().getOrDefault(direction, MachineSideConfiguration.DISABLED).name());
        }
        parent.put("sides", tag);
    }

    public void updateSide(Direction side, MachineSideConfiguration updated) {
        getSideConfigurations().put(side, updated);
        if (getWorld().isRemote) {
            packetManager.getChannel().sendToServer(packetManager.getMachineSideConfigureFactory().create(getPos(), side, updated));
        } else {
            getWorld().notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
            markDirty();
        }
    }

    public boolean running() {
        return running;
    }

    public Map<Direction, MachineSideConfiguration> getSideConfigurations() {
        for (MachineSideConfiguration configuration : sideConfigurations.values()) {
            if (configuration == MachineSideConfiguration.FRONT) {
                return sideConfigurations;
            }
        }
        for (Direction direction : DIRECTION_CACHE) {
            sideConfigurations.putIfAbsent(direction, MachineSideConfiguration.DISABLED);
        }
        if (hasWorld()) {
            sideConfigurations.put(getBlockState().get(MachineProperties.FACING), MachineSideConfiguration.FRONT);
        }
        return sideConfigurations;
    }

    @Override
    public IModelData getModelData() {
        ModelDataMap.Builder builder = new ModelDataMap.Builder();
        for (Entry<Direction, MachineSideConfiguration> entry : getSideConfigurations().entrySet()) {
            builder.withInitial(MachineProperties.get(entry.getKey()), entry.getValue());
        }
        builder.withInitial(MachineProperties.get(getBlockState().get(MachineProperties.FACING)), MachineSideConfiguration.FRONT)
                .withInitial(MachineProperties.RUNNING, running());
        return builder.build();
    }

    @Override
    public void tick() {
        if (machine == null) {
            System.out.println("MACHINE NULL on tick! (client: " + world.isRemote + ")");
            return;
        }
        if (!world.isRemote) {
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
                        running = false;
                        processingTick = 0;
                        recipes.get(machine.getRecipeType()).parallelStream().filter(recipe -> recipe.canProcess(itemHandler.getStacks(), inputSlots)).findFirst().ifPresent(r -> {
                            currentRecipe = r;
                            running = true;
                        });
                        maxTicks = energyTiers.getProcessingTicks(voltage); //TODO power multiplier
                        updateBlock();
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
                        running = false;
                        processingTick = 0;
                        recalculateRecipe = true;
                        updateBlock();
                        return;
                    }
                    currentRecipe.process(itemHandler.getStacks(), inputSlots, (slot, item) -> {
                        itemHandler.setStackInSlot(slot, item);
                    });
                });
            }
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Machine");
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return containerFactory.create(windowId, playerInventory, this, tracked);
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
            recalculateRecipe = true;
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
            recalculateRecipe = true;
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

    private void updateModel() {
        if (getWorld() != null) {
            requestModelDataUpdate();
            Minecraft.getInstance().worldRenderer.markBlockRangeForRenderUpdate(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ());
        }
    }

    private void updateBlock() {
        if (getWorld() != null) {
            getWorld().notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
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
            MachineSideConfiguration config = getSideConfigurations().get(side);
            if (config == MachineSideConfiguration.BOTH || config == MachineSideConfiguration.OUTPUT) {
                return delegate.extractItem(slot, amount, simulate);
            } else {
                return ItemStack.EMPTY;
            }
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            MachineSideConfiguration config = getSideConfigurations().get(side);
            if (config == MachineSideConfiguration.BOTH || config == MachineSideConfiguration.INPUT) {
                return delegate.insertItem(slot, stack, simulate);
            } else {
                return ItemStack.EMPTY;
            }
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
        public int getSlotLimit(int slot) {
            return delegate.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return delegate.isItemValid(slot, stack);
        }
    }
}
