package co.q64.dynamicalsystems.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import net.minecraft.util.Identifier;

@Singleton
public class ItemIdentifierUtil {
	protected @Inject @ModId String modId;
	protected @Inject @SharedNamespace String shared;
	protected @Inject MaterialItemNameGenerator materialItemNameGenerator;
	protected @Inject ItemIdentifierUtil() {}

	public Identifier getIdentifier(BaseItem item) {
		return new Identifier(modId, item.getId());
	}
	
	public Identifier getIdentifier(MaterialItem item) {
		return new Identifier(modId, item.getId());
	}
	
	public Identifier getSharedIdentifier(Component component, Material material) {
		return new Identifier(shared, materialItemNameGenerator.generateId(component, material));
	}
}
