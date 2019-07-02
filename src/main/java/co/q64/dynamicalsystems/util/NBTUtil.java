package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineRegistry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;

@Singleton
public class NBTUtil {
    protected @Inject MachineRegistry registry;

    protected @Inject NBTUtil() {}

    public String serializeMachine(Machine machine) {
        return machine.getId();
    }

    public Machine deserializeMachine(String tag) {
        return registry.getIds().get(tag);
    }

    public String serializeVoltage(Voltage voltage) {
        return voltage.name();
    }

    public Voltage deserializeVoltage(String tag) {
        return Arrays.stream(Voltage.getAll()).filter(v -> v.name().equals(tag)).findAny().get();
    }
}
