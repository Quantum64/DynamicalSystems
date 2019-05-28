package co.q64.dynamicalsystems.util;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import net.minecraft.util.Identifier;

@Singleton
public class ItemUtil {
	protected @Inject MaterialItemLoader materialItemLoader;

	protected @Inject ItemUtil() {}

	public List<MaterialItem> getMaterialItems() {
		return materialItemLoader.getItems();
	}

	public Optional<MaterialItem> getMaterialItem(Identifier identifier) {
		return Optional.ofNullable(materialItemLoader.getIdentifierCache().get(identifier));
	}
}
