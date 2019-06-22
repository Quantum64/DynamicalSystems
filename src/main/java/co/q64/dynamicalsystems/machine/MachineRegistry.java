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

    protected @Inject MachineRegistry() {}

    protected void register(Machine machine) {
        machines.add(machine);
    }
}
