package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.block.MachineBlockFactory;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.group.MachinesGroup;
import co.q64.dynamicalsystems.machine.Machine;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@AutoFactory
public class MachineBlockItem extends BaseBlockItem {
    private Machine machine;
    private @Getter Voltage voltage;
    private @Getter MachineBlock block;

    protected MachineBlockItem(Machine machine, Voltage voltage, @Provided MachineBlockFactoryFactory blockFactoryFactory, @Provided MachinesGroup group) {
        super(blockFactoryFactory.getFactory().create(voltage.getDisplayName(machine.getName())), new Properties().group(group));
        this.machine = machine;
        this.voltage = voltage;
        this.block = (MachineBlock) super.getBlock();
        block.setMachine(machine, voltage);
    }

    @Override
    public MachineBlock getBlock() {
        return block;
    }

    public Machine getMachine() {
        return machine;
    }

    public String getOffModel() {
        return "block/" + getId() + "_off";
    }

    public String getOnModel() {
        return "block/" + getId() + "_on";
    }

    @Singleton
    public static class MachineBlockFactoryFactory {
        protected @Inject MachineBlockFactoryFactory() {}

        protected @Getter @Inject MachineBlockFactory factory;
    }
}
