package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.unification.Unification;

import javax.inject.Inject;

public class SidePanel extends Panel {
    protected @Inject SidePanel(Unification unification) {
        this.icon = unification.getStack(unification.getComponents().gear, unification.getMaterials().iron).getItem();
    }

}
