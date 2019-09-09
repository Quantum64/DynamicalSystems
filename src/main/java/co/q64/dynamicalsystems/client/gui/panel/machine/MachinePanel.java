package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.client.gui.screen.MachineScreen;
import co.q64.dynamicalsystems.unification.Unification;

import javax.inject.Inject;

public abstract class MachinePanel extends Panel {
    protected @Inject Unification unification;

    protected MachineScreen screen;

    public MachinePanel setup(MachineScreen screen) {
        this.screen = screen;
        return this;
    }

    public void render(int x, int y) {

    }
}
