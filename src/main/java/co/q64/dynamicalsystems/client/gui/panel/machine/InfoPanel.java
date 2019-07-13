package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.unification.Unification;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.item.ItemStack;

@AutoFactory
public class InfoPanel extends Panel {
    protected InfoPanel(@Provided Unification unification) {
        this.color = 0xff00ff00;
        this.icon = new ItemStack(unification.getStack(unification.getComponents().dust, unification.getMaterials().uranium).getItem());
    }

}
