package co.q64.dynamicalsystems.client.gui.screen;

import co.q64.dynamicalsystems.client.gui.ModularGuiRender;
import co.q64.dynamicalsystems.gui.MachineContainer;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

@AutoFactory
public class MachineScreen extends ContainerScreen<MachineContainer> {
    private MachineContainer container;
    private ModularGuiRender render;

    public MachineScreen(MachineContainer container, PlayerInventory playerInventory, ITextComponent title, @Provided ModularGuiRender render) {
        super(container, playerInventory, title);
        this.container = container;
        this.render = render;
        this.xSize = container.getWidth();
        this.ySize = container.getHeight();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        render.render(container, guiLeft, guiTop);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }
}
