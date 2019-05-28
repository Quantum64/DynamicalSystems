package co.q64.dynamicalsystems.util;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Singleton
public class ItemRegistrationUtil {
	protected @Inject ItemIdentifierUtil identifierUtil;

	private Map<Identifier, MaterialItem> identifierCache = new HashMap<>();

	protected @Inject ItemRegistrationUtil() {}

	public void registerItem(BaseItem item) {
		Registry.register(Registry.ITEM, identifierUtil.getIdentifier(item), item);
	}
}
