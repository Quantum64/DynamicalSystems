package co.q64.dynamicalsystems.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.item.BaseItem;
import net.minecraft.util.Identifier;

@Singleton
public class ItemIdentifierUtil {
	protected @Inject @ModId String modId;

	protected @Inject ItemIdentifierUtil() {}

	public Identifier getIdentifier(BaseItem item) {
		return new Identifier(modId, item.getId());
	}
	
	
}
