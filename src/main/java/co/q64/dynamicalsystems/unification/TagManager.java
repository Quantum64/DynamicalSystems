package co.q64.dynamicalsystems.unification;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.util.ItemIdentifierUtil;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;

@Singleton
public class TagManager {
	protected @Inject @SharedNamespace String namespace;
	protected @Inject MaterialItemLoader materialItemLoader;
	protected @Inject ItemIdentifierUtil identifierUtil;

	public @Inject TagManager() {}

	public void load() {

	}

	public Tag<Item> getTag(Component component, Material material) {
		return ItemTags.getContainer().getOrCreate(identifierUtil.getSharedIdentifier(component, material));
	}

}
