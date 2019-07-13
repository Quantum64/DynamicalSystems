package co.q64.dynamicalsystems.client.gui;

import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.client.gui.screen.DynamicScreen;
import co.q64.dynamicalsystems.gui.DynamicContainer;
import co.q64.dynamicalsystems.resource.Translations;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import net.minecraft.util.text.ITextComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ModularGuiRender {
    public static final int TMP_FONT_COLOR = 4210752;

    private static final int PANEL_ICON_WIDTH = 24;
    private static final int PANEL_ICON_HEIGHT = 24;

    protected @Inject GuiDynamicRender dynamicRender;
    protected @Inject IdentifierUtil identifiers;

    private ITextComponent playerInventory;

    protected @Inject ModularGuiRender(Translations translations) {
        this.playerInventory = translations.playerInventory;
    }

    public void render(DynamicScreen<?> screen) {
        int x = screen.getGuiLeft(), y = screen.getGuiTop();
        DynamicContainer<?> container = screen.getContainer();
        dynamicRender.drawGuiPanel(x, y, container.getWidth(), container.getHeight());
        screen.getFontRenderer().drawString(playerInventory.getFormattedText(), x + 4, y + container.getPlayerInventoryHeight(), TMP_FONT_COLOR);
        int leftGutterY = y, rightGutterY = y;
        for (Panel panel : screen.getPanels()) {
            int panelX = 0;
            int panelY = panel.isLeft() ? leftGutterY : rightGutterY;
            if (panel.isExpanded()) {
                panelX = panel.isLeft() ? x - panel.getExpandedWidth() : x + container.getWidth();
                dynamicRender.drawGuiPanel(panelX, panelY, panel.getExpandedWidth(), panel.getExpandedHeight(), panel.getColor());
                screen.getFontRenderer().drawString(panel.getTranslatedName().getFormattedText(), panelX + 18, panelY + 10, ModularGuiRender.TMP_FONT_COLOR);
                panel.render(panelX, panelY + 16);
                if (panel.isLeft()) {
                    leftGutterY += panel.getExpandedHeight();
                } else {
                    rightGutterY += panel.getExpandedHeight();
                }
            } else {
                panelX = panel.isLeft() ? x - PANEL_ICON_WIDTH : x + container.getWidth();
                dynamicRender.drawGuiPanel(panelX, panelY, PANEL_ICON_WIDTH, PANEL_ICON_HEIGHT, panel.getColor());
                if (panel.isLeft()) {
                    leftGutterY += PANEL_ICON_HEIGHT;
                } else {
                    rightGutterY += PANEL_ICON_HEIGHT;
                }
            }
            screen.drawItemStack(panel.getIcon(), panelX + 4, panelY + 4);
        }
    }

    public boolean click(DynamicScreen<?> screen, double dx, double dy, int something) {
        int x = screen.getGuiLeft(), y = screen.getGuiTop();
        DynamicContainer<?> container = screen.getContainer();
        int leftGutterY = y, rightGutterY = y;
        for (Panel panel : screen.getPanels()) {
            int panelY = panel.isLeft() ? leftGutterY : rightGutterY;
            if (panel.isExpanded()) {
                int panelX = panel.isLeft() ? x - panel.getExpandedWidth() : x + container.getWidth();
                if (dx > panelX && dx < panelX + panel.getExpandedWidth() && dy > panelY && dy < panelY + panel.getExpandedHeight()) {
                    panel.click((int) dx - panelX, (int) dy - panelY - 16);
                }
                if (panel.isLeft()) {
                    leftGutterY += panel.getExpandedHeight();
                } else {
                    rightGutterY += panel.getExpandedHeight();
                }
            } else {
                int panelX = panel.isLeft() ? x - PANEL_ICON_WIDTH : x + container.getWidth();
                if (dx > panelX && dx < panelX + PANEL_ICON_WIDTH && dy > panelY && dy < panelY + PANEL_ICON_HEIGHT) {
                    for (Panel p : screen.getPanels()) {
                        p.setExpanded(false);
                    }
                    panel.setExpanded(true);
                }
                if (panel.isLeft()) {
                    leftGutterY += PANEL_ICON_HEIGHT;
                } else {
                    rightGutterY += PANEL_ICON_HEIGHT;
                }
            }
        }
        return false;
    }
}
