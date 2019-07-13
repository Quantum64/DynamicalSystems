package co.q64.dynamicalsystems.gui;

import co.q64.dynamicalsystems.client.gui.DefaultMachineLayout;
import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import co.q64.dynamicalsystems.tile.MachineTile;
import co.q64.dynamicalsystems.tile.MachineTile.MachineItemHandler;
import co.q64.dynamicalsystems.util.Point;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.items.SlotItemHandler;

import java.util.ArrayList;
import java.util.List;

@AutoFactory
public class MachineContainer extends DynamicContainer<MachineContainer> {
    private MachineItemHandler inventory;
    private PlayerInventory playerInventory;
    private int machineSlots;
    private @Getter List<Slot> inputSlots = new ArrayList<>();
    private @Getter List<Slot> outputSlots = new ArrayList<>();
    private @Getter IIntArray tracked;
    private @Getter MachineTile tile;

    protected MachineContainer(int windowId, PlayerInventory playerInventory, MachineTile tile, @Provided MachineContainerType type, @Provided DefaultMachineLayout layout) {
        this(windowId, playerInventory, tile, new IntArray(4), type, layout);
    }

    protected MachineContainer(int windowId, PlayerInventory playerInventory, MachineTile tile, IIntArray tracked, @Provided MachineContainerType type, @Provided DefaultMachineLayout layout) {
        super(windowId, playerInventory, type);
        this.playerInventory = playerInventory;
        this.tile = tile;
        this.tracked = tracked;
        tile.getItemHandler().ifPresent(handler -> inventory = handler);
        int index = 0;
        for (int i = 0; i < tile.getInputSlots(); i++) {
            Point location = tile.getMachine().getInputSlotLocations().size() > i ? tile.getMachine().getInputSlotLocations().get(i) :
                    layout.getInputSlotLocations(tile.getInputSlots() - tile.getMachine().getInputSlotLocations().size()).get(i - tile.getMachine().getInputSlotLocations().size());
            addInputSlot(new MachineSlotItemHandler(inventory, index++, location.getX(), location.getY()));
        }
        for (int i = 0; i < tile.getOutputSlots(); i++) {
            Point location = tile.getMachine().getOutputSlotLocations().size() > i ? tile.getMachine().getOutputSlotLocations().get(i) :
                    layout.getOutputSlotLocations(tile.getInputSlots() - tile.getMachine().getOutputSlotLocations().size()).get(i - tile.getMachine().getOutputSlotLocations().size());
            addOutputSlot(new MachineSlotItemHandler(inventory, index++, location.getX(), location.getY()));
        }
        machineSlots = tile.getInputSlots() + tile.getOutputSlots();

        setupInventory(tile.getMachine().getGuiHeight());
        trackIntArray(tracked);
    }

    private void addInputSlot(Slot slot) {
        inputSlots.add(slot);
        addSlot(slot);
    }

    private void addOutputSlot(Slot slot) {
        outputSlots.add(slot);
        addSlot(slot);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index < machineSlots) {
                if (!this.mergeItemStack(stack, machineSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
                boolean canInsert = false;
                for (int i = 0; i < machineSlots; i++) {
                    canInsert = !inventory.extractItem(i, 1, true).isEmpty();
                    if (canInsert) {
                        break;
                    }
                }
                if (canInsert) {
                    if (!this.mergeItemStack(stack, 0, machineSlots, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < machineSlots + 27) {
                    if (!this.mergeItemStack(stack, machineSlots + 37, machineSlots + 36, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < machineSlots + 36 && !this.mergeItemStack(stack, machineSlots, machineSlots + 27, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    private class MachineSlotItemHandler extends SlotItemHandler {
        private MachineItemHandler handler;
        private int index;

        public MachineSlotItemHandler(MachineItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
            this.handler = itemHandler;
            this.index = index;
        }

        @Override
        public boolean canTakeStack(PlayerEntity playerIn) {
            return !handler.extractItem(index, 1, true).isEmpty();
        }
    }
}
