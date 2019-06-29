package co.q64.dynamicalsystems.client.gui;

import co.q64.dynamicalsystems.gui.DynamicContainer;
import net.minecraft.inventory.container.Slot;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ModularGuiRender {
    protected @Inject GuiDynamicRender dynamicRender;

    protected @Inject ModularGuiRender() {}

    public void render(DynamicContainer<?> container, int x, int y) {
        dynamicRender.drawGuiPanel(x, y, container.getWidth(), container.getHeight());
        for (Slot slot : container.inventorySlots) {
            dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1);
        }
    }
}
