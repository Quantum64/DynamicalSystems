package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.block.item.MachineBlockItemFactory;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.util.RegistryUtil;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MachineProcessor {
    protected @Inject Machines machines;
    protected @Inject MachineBlockItemFactory itemFactory;
    protected @Inject RegistryUtil registryUtil;

    private @Getter List<MachineBlockItem> items = new ArrayList<>();

    protected @Inject MachineProcessor() {}

    @Inject
    protected void loadMachines() {
        for (Machine machine : machines.getAll()) {
            for (Voltage voltage : Voltage.getAll()) {
                if (machine.getGenerateTier().test(voltage)) {
                    MachineBlockItem item = itemFactory.create(machine, voltage);
                    items.add(item);
                    registryUtil.registerBlock(item);
                }
            }
        }
    }
}
