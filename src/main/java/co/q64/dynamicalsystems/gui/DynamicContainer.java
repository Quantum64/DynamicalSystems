package co.q64.dynamicalsystems.gui;

import lombok.Getter;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class DynamicContainer<T extends Container> extends Container {
    private @Getter int x, y, width, height;
    private @Getter int playerInventoryHeight;
    //private int machineSlots;
    private IItemHandler playerInventory;

    public DynamicContainer(int windowId, PlayerInventory inventory, ContainerType<T> type) {
        super(type, windowId);
        this.playerInventory = new InvWrapper(inventory);
    }


    protected void setupInventory(int h) {
        int topRow = 0;
        int leftCol = 7;
        //machineSlots = inventorySlots.size();
        //for (Slot slot : inventorySlots) {
        //   topRow = Math.max(slot.yPos + 18, topRow);
        //}
        topRow = h;
        //topRow += 13;
        playerInventoryHeight = topRow - 12;
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
        topRow += 18;

        height = topRow + 7;
        width = 176;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
}
