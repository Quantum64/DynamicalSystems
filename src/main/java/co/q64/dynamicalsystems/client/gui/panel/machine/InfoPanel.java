package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.screen.MachineScreen;
import co.q64.dynamicalsystems.resource.Translations;
import co.q64.dynamicalsystems.unification.Unification;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.item.ItemStack;

@AutoFactory
public class InfoPanel extends MachinePanel {

    protected InfoPanel(MachineScreen screen, @Provided Unification unification, @Provided Translations translations) {
        super(screen);
        this.translatedName = translations.machineInfo;
        this.color = 0xff00ff00;
        this.icon = new ItemStack(unification.getStack(unification.getComponents().dust, unification.getMaterials().uranium).getItem());
    }

    public void render(int x, int y) {
        super.render(x, y);
    }
}
