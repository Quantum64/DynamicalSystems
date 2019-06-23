package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.block.MachineBlockFactory;
import co.q64.dynamicalsystems.group.MaterialsGroup;
import co.q64.dynamicalsystems.machine.Machine;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@AutoFactory
public class MachineBlockItem extends BaseBlockItem {
    private Machine machine;
    private MachineBlock block;

    protected MachineBlockItem(Machine machine, @Provided MachineBlockFactoryFactory blockFactoryFactory, @Provided MaterialsGroup group) {
        super(blockFactoryFactory.getFactory().create(""), new Properties().group(group));
        this.machine = machine;
        this.block = (MachineBlock) super.getBlock();
    }

    @Override
    public MachineBlock getBlock() {
        return block;
    }

    public Machine getMachine() {
        return machine;
    }

    @Singleton
    public static class MachineBlockFactoryFactory {
        protected @Inject MachineBlockFactoryFactory() {}

        protected @Getter @Inject MachineBlockFactory factory;
    }
}
