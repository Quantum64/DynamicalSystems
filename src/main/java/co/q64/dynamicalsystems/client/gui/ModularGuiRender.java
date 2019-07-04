package co.q64.dynamicalsystems.client.gui;

import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.gui.DynamicContainer;
import net.minecraft.inventory.container.Slot;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ModularGuiRender {
    private static final int PANEL_ICON_WIDTH = 25;
    private static final int PANEL_ICON_HEIGHT = 25;

    protected @Inject GuiDynamicRender dynamicRender;

    protected @Inject ModularGuiRender() {}

    public void render(DynamicContainer<?> container, int x, int y) {
        dynamicRender.drawGuiPanel(x, y, container.getWidth(), container.getHeight());
        for (Slot slot : container.inventorySlots) {
            dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1);
        }
        int leftGutterY = y, rightGutterY = y;
        for (Panel panel : container.getPanels()) {
            if (panel.isExpanded()) {

            } else {
                int panelX = panel.isLeft() ? x - PANEL_ICON_WIDTH : x + container.getWidth();
                int panelY = panel.isLeft() ? leftGutterY : rightGutterY;
                dynamicRender.drawGuiPanel(panelX, panelY, PANEL_ICON_WIDTH, PANEL_ICON_HEIGHT, panel.getColor());
                if (panel.isLeft()) {
                    leftGutterY += PANEL_ICON_HEIGHT;
                } else {
                    rightGutterY += PANEL_ICON_HEIGHT;
                }
            }
        }
    }
}
