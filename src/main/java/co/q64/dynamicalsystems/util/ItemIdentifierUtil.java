package co.q64.dynamicalsystems.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.unification.SharedItem;
import co.q64.dynamicalsystems.util.identifier.IdentifierUtil;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Singleton
public class ItemIdentifierUtil {
	protected @Inject IdentifierUtil identifierUtil;
	protected @Inject @SharedNamespace String shared;
	protected @Inject MaterialItemNameGenerator materialItemNameGenerator;

	protected @Inject ItemIdentifierUtil() {}

	public Identifier getIdentifier(BaseItem item) {
		return identifierUtil.get(item.getId());
	}

	public Identifier getIdentifier(MaterialItem item) {
		return identifierUtil.get(item.getId());
	}

	public Identifier getSharedIdentifier(SharedItem si) {
		if (si.getComponent().isPresent() && si.getMaterial().isPresent()) {
			return new Identifier(shared, materialItemNameGenerator.generateId(si.getComponent().get(), si.getMaterial().get()));
		}
		for (Item item : si.getItems()) {
			if (item instanceof BaseItem) {
				return new Identifier(shared, ((BaseItem) item).getId());
			}
		}
		if (!si.getFirst().isPresent()) {
			throw new IllegalStateException("SharedItem is empty!"); // Probably impossible
		}
		return new Identifier(shared, Registry.ITEM.getId(si.getFirst().get()).getPath());
	}
}
