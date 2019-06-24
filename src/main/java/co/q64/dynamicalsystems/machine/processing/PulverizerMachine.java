package co.q64.dynamicalsystems.machine.processing;

import co.q64.dynamicalsystems.machine.Machine;

import javax.inject.Inject;

public class PulverizerMachine extends Machine {
    protected @Inject PulverizerMachine() {
        name = "Pulverizer";
        overlayOnTexture = "pulverizer_overlay_on";
        overlayOffTexture = "pulverizer_overlay_off";
    }
}
