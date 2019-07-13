package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.ModularGuiRender;
import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.client.gui.screen.MachineScreen;

public abstract class MachinePanel extends Panel {
    protected MachineScreen screen;

    protected MachinePanel(MachineScreen screen) {
        this.screen = screen;
    }

    public void render(int x, int y) {

    }
}
