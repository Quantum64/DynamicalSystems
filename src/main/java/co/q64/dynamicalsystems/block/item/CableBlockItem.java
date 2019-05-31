package co.q64.dynamicalsystems.block.item;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.dynamicalsystems.binders.ConstantBinders.MaterialsItemGroup;
import co.q64.dynamicalsystems.block.CableBlock;
import co.q64.dynamicalsystems.block.CableBlockFactory;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import lombok.Getter;
import net.minecraft.item.ItemGroup;

@AutoFactory
public class CableBlockItem extends MaterialBlockItem implements MaterialItem {
	private @Getter CableBlock block;

	public CableBlockItem(Material material, Component component, @Provided CableBlockFactoryFactory blockFactoryFactory, @Provided MaterialItemNameGenerator generator, @Provided @MaterialsItemGroup ItemGroup group) {
		super(material, component, blockFactoryFactory.getFactory().create(generator.generate(component, material)), group);
		this.block = (CableBlock) super.getBlock();
	}

	@Singleton
	public static class CableBlockFactoryFactory {
		protected @Inject CableBlockFactoryFactory() {}

		protected @Getter @Inject CableBlockFactory factory;
	}
}
