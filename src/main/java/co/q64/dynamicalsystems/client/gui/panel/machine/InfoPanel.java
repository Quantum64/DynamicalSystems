package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.unification.Unification;

import javax.inject.Inject;

public class InfoPanel extends Panel {
    protected @Inject InfoPanel(Unification unification) {
        this.color = 0xff00ff00;
        this.icon = unification.getStack(unification.getComponents().dust, unification.getMaterials().uranium).getItem();
    }

}
