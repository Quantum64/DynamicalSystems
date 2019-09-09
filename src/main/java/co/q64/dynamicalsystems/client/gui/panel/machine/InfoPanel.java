package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.screen.MachineScreen;
import net.minecraft.item.ItemStack;

import javax.inject.Inject;

public class InfoPanel extends MachinePanel {

    protected @Inject InfoPanel() {}

    public InfoPanel setup(MachineScreen screen) {
        super.setup(screen);
        this.translatedName = translations.machineInfo;
        this.color = 0xff00ff00;
        this.icon = new ItemStack(unification.getStack(unification.getComponents().dust, unification.getMaterials().uranium).getItem());
        return this;
    }

    public void render(int x, int y) {
        super.render(x, y);
    }
}
