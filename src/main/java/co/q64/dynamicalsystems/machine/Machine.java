package co.q64.dynamicalsystems.machine;

import javax.inject.Inject;

public abstract class Machine {
    protected @Inject MachineRegistry registry;

    @Inject
    protected void register() {
        registry.register(this);
    }
}
