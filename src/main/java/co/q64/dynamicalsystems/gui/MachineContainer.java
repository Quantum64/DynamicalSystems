package co.q64.dynamicalsystems.gui;

import co.q64.dynamicalsystems.client.gui.panel.machine.InfoPanel;
import co.q64.dynamicalsystems.client.gui.panel.machine.SidePanel;
import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import co.q64.dynamicalsystems.tile.MachineTile;
import co.q64.dynamicalsystems.tile.MachineTile.MachineItemHandler;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

@AutoFactory
public class MachineContainer extends DynamicContainer<MachineContainer> {
    private MachineItemHandler inventory;
    private PlayerInventory playerInventory;
    private int machineSlots;
    private MachineTile tile;

    protected MachineContainer(int windowId, PlayerInventory playerInventory, MachineTile tile, @Provided MachineContainerType type,
                               @Provided InfoPanel infoPanel, @Provided SidePanel sidePanel) {
        super(windowId, playerInventory, type);
        this.playerInventory = playerInventory;
        this.tile = tile;
        tile.getItemHandler().ifPresent(handler -> inventory = handler);
        int index = 0;
        for (int i = 0; i < tile.getInputSlots(); i++) {
            addSlot(new MachineSlotItemHandler(inventory, index++, 18 * index, 10));
        }
        for (int i = 0; i < tile.getOutputSlots(); i++) {
            addSlot(new MachineSlotItemHandler(inventory, index++, (18 * index) + 10, 10));
        }
        machineSlots = tile.getInputSlots() + tile.getOutputSlots();

        getPanels().add(infoPanel);
        getPanels().add(sidePanel);

        setupInventory();
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
