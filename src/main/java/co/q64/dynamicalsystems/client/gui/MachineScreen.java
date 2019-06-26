package co.q64.dynamicalsystems.client.gui;

import co.q64.dynamicalsystems.gui.MachineContainer;
import com.google.auto.factory.AutoFactory;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

@AutoFactory
public class MachineScreen extends ContainerScreen<MachineContainer> {
    public MachineScreen(MachineContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }
}
