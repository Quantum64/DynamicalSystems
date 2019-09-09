package co.q64.dynamicalsystems.client.gui.screen;

import co.q64.dynamicalsystems.client.gui.MachineGuiRender;
import co.q64.dynamicalsystems.client.gui.panel.machine.InfoPanel;
import co.q64.dynamicalsystems.client.gui.panel.machine.SidePanel;
import co.q64.dynamicalsystems.gui.MachineContainer;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import javax.inject.Provider;

@AutoFactory
public class MachineScreen extends DynamicScreen<MachineContainer> {
    private MachineContainer container;
    private MachineGuiRender render;

    public MachineScreen(MachineContainer container, PlayerInventory playerInventory, ITextComponent title, @Provided MachineGuiRender render,
                         @Provided Provider<InfoPanel> infoPanel,
                         @Provided Provider<SidePanel> sidePanel) {
        super(container, playerInventory, title, render);
        this.container = container;
        this.render = render;
        this.xSize = container.getWidth();
        this.ySize = container.getHeight();

        getPanels().add(infoPanel.get().setup(this));
        getPanels().add(sidePanel.get().setup(this));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        render.render(this);
        render.renderSlots(this);
        render.renderProgress(this);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
