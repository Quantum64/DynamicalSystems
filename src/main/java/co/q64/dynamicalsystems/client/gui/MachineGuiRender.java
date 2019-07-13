package co.q64.dynamicalsystems.client.gui;

import co.q64.dynamicalsystems.client.gui.bar.Bar;
import co.q64.dynamicalsystems.client.gui.screen.MachineScreen;
import co.q64.dynamicalsystems.gui.MachineContainer;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.resource.Translations;
import co.q64.dynamicalsystems.util.Point;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IIntArray;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MachineGuiRender extends ModularGuiRender {
    private Map<Bar, ResourceLocation> barTextures = new HashMap<>();
    private Map<Bar, ResourceLocation> barOverlays = new HashMap<>();

    protected @Inject MachineGuiRender(Translations service) {
        super(service);
    }

    public void renderSlots(MachineScreen screen) {
        int x = screen.getGuiLeft(), y = screen.getGuiTop();
        MachineContainer container = screen.getContainer();
        for (Slot slot : container.getInputSlots()) {
            if (container.getTile().getSideConfigurations().values().contains(MachineSideConfiguration.INPUT)) {
                dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1, 0xff0000ff);
            } else {
                dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1);
            }
        }
        for (Slot slot : container.getOutputSlots()) {
            if (container.getTile().getSideConfigurations().values().contains(MachineSideConfiguration.OUTPUT)) {
                dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1, 0xffff8800);
            } else {
                dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1);
            }
        }
        for (Slot slot : container.inventorySlots) {
            if (container.getOutputSlots().contains(slot) || container.getInputSlots().contains(slot)) {
                continue;
            }
            dynamicRender.drawItemSlot(x + slot.xPos - 1, y + slot.yPos - 1);
        }
    }

    public void renderProgress(MachineScreen screen) {
        screen.getFontRenderer().drawString(screen.getContainer().getTile().getMachine().getTranslatedName().getFormattedText(), screen.getGuiLeft() + 4, screen.getGuiTop() + 4, TMP_FONT_COLOR);
        IIntArray tracked = screen.getContainer().getTracked();
        int processingTicks = tracked.get(0), maxTicks = tracked.get(1);
        float progress = processingTicks / Float.valueOf(maxTicks);
        int index = 0;
        for (Bar bar : screen.getContainer().getTile().getMachine().getBars()) {
            int x = screen.getGuiLeft(), y = screen.getGuiTop();
            if (screen.getContainer().getTile().getMachine().getBarLocations().size() > index) {
                Point offset = screen.getContainer().getTile().getMachine().getBarLocations().get(index);
                x += offset.getX();
                y += offset.getY();
            }
            int barWidth = bar.getWidth(), barHeight = bar.getHeight();
            float floorProgress = ((int) (progress * bar.getWidth())) / Float.valueOf(bar.getWidth());
            float u1 = 0f, v1 = 0f, u2 = 1f, v2 = 1f;
            switch (bar.getDirection()) {
                case LEFT:
                    barWidth = (int) (barWidth * progress);
                    u1 = 1f - floorProgress;
                    break;
                case RIGHT:
                    barWidth = (int) (barWidth * progress);
                    u2 = floorProgress;
                    break;
                case UP:
                    barHeight = (int) (barHeight * progress);
                    v1 = 1f - floorProgress;
                    break;
                case DOWN:
                    barHeight = (int) (barHeight * progress);
                    v2 = floorProgress;
                    break;
            }
            dynamicRender.rect(barTextures.computeIfAbsent(bar, b -> identifiers.get(b.getBaseTexture())), x, y, bar.getWidth(), bar.getHeight(), 0xffffffff);
            if (barWidth > 0) {
                dynamicRender.rect(barOverlays.computeIfAbsent(bar, b -> identifiers.get(b.getOverlayTexture())), x, y, barWidth, barHeight, u1, v1, u2, v2, 0xffffffff);
            }
            index++;
        }
    }
}
