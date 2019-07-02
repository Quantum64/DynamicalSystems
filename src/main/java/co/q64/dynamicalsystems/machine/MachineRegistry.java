package co.q64.dynamicalsystems.machine;

import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class MachineRegistry {
    private @Getter List<Machine> machines = new ArrayList<>();
    private @Getter Map<String, Machine> ids = new HashMap<>();
    private boolean frozen;

    protected @Inject MachineRegistry() {}

    protected void register(Machine machine) {
        if (frozen) {
            throw new IllegalStateException("It's too late to register machines");
        }
        machines.add(machine);
        ids.put(machine.getId(), machine);
    }

    public void freeze() {
        frozen = true;
    }
}
