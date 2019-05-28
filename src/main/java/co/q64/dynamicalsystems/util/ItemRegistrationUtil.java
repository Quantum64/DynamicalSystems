package co.q64.dynamicalsystems.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import net.minecraft.util.registry.Registry;

@Singleton
public class ItemRegistrationUtil {
	protected @Inject ItemIdentifierUtil identifierUtil;

	protected @Inject ItemRegistrationUtil() {}

	public void registerItem(BaseItem item) {
		Registry.register(Registry.ITEM, identifierUtil.getIdentifier(item), item);
	}

	public void registerMaterial(MaterialItem item) {
		Registry.register(Registry.ITEM, identifierUtil.getIdentifier(item), item.getItem());
		if (item.isBlock()) {
			Registry.register(Registry.BLOCK, identifierUtil.getIdentifier(item), item.getBlock());
		}
	}
}
