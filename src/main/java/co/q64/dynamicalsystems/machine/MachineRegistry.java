package co.q64.dynamicalsystems.machine;

import lombok.AccessLevel;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MachineRegistry {
    private @Getter(AccessLevel.PROTECTED) List<Machine> machines = new ArrayList<>();
    private boolean frozen;

    protected @Inject MachineRegistry() {}

    protected void register(Machine machine) {
        if (frozen) {
            throw new IllegalStateException("It's too late to register machines");
        }
        machines.add(machine);
    }

    public void freeze() {
        frozen = true;
    }
}
