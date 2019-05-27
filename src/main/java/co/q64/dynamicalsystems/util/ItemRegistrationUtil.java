package co.q64.dynamicalsystems.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.item.BaseItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Singleton
public class ItemRegistrationUtil {
	protected @Inject @ModId String modId;

	protected @Inject ItemRegistrationUtil() {}

	public void registerItem(BaseItem item) {
		Registry.register(Registry.ITEM, new Identifier(modId, item.getName().replace(" ", "_").toLowerCase()), item);
	}
}
