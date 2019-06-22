package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.machine.processing.PulverizerMachine;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class Machines {
    protected @Inject MachineRegistry registry;

    public @Inject PulverizerMachine pulverizer;

    protected @Inject Machines() {}

    public List<Machine> getAll() {
        return registry.getMachines();
    }
}
