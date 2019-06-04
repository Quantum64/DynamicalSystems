package co.q64.dynamicalsystems.block.item;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.dynamicalsystems.binders.ConstantBinders.MachinesItemGroup;
import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.block.MachineBlockFactory;
import co.q64.dynamicalsystems.machine.Machine;
import lombok.Getter;
import net.minecraft.item.ItemGroup;

@AutoFactory
public class MachineBlockItem extends BaseBlockItem {
	private Machine machine;
	private MachineBlock block;

	protected MachineBlockItem(Machine machine, @Provided MachineBlockFactoryFactory blockFactoryFactory, @Provided @MachinesItemGroup ItemGroup group) {
		super(blockFactoryFactory.getFactory().create(""), new Settings().itemGroup(group));
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
