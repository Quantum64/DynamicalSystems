package co.q64.dynamicalsystems.unification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import lombok.Getter;
import net.minecraft.item.Item;

@Singleton
public class Unification {
	protected @Inject MaterialItemLoader materialItemLoader;
	protected @Inject SharedItemFactory sharedItemFactory;
	protected @Inject SharedItemStackFactory sharedItemStackFactory;

	private @Getter List<SharedItem> items = new ArrayList<>(1);

	protected @Inject Unification() {}

	public SharedItem get(Component component, Material material) {
		for (SharedItem si : items) {
			// TODO maybe cache in map
			if (si.getComponent().orElse(null) == component && si.getMaterial().orElse(null) == material) {
				return si;
			}
		}
		SharedItem result = sharedItemFactory.create(component, material);
		items.add(result);
		return result;
	}

	public SharedItem get(Item item) {
		for (SharedItem si : items) {
			if (si.getItems().contains(item)) {
				return si;
			}
		}
		SharedItem result = sharedItemFactory.create().unify(item);
		items.add(result);
		return result;
	}

	public SharedItemStack getStack(Component component, Material material, int amount) {
		return sharedItemStackFactory.create(get(component, material), amount);
	}

	public SharedItemStack getStack(Component component, Material material) {
		return getStack(component, material, 1);
	}

	public SharedItemStack getStack(Item item, int amount) {
		return sharedItemStackFactory.create(get(item), amount);
	}

	public SharedItemStack getStack(Item item) {
		return getStack(item, 1);
	}

	public SharedItemStack getStack(SharedItem item, int amount) {
		return sharedItemStackFactory.create(item, amount);
	}

	public SharedItemStack getStack(SharedItem item) {
		return getStack(item, 1);
	}

	/*
	public ItemStack getMaterialStack(Component component, Material material) {
		return getMaterialStack(component, material, 1);
	}
	
	public ItemStack getMaterialStack(Component component, Material material, int size) {
		Optional<MaterialItem> generated = materialItemLoader.getItem(component, material);
		if (generated.isPresent()) {
			return new ItemStack(generated.get().getItem(), size);
		}
		return ItemStack.EMPTY;
	}
	*/

	@Inject
	protected void init() {
		for (Entry<Component, Map<Material, MaterialItem>> component : materialItemLoader.getItemMap().entrySet()) {
			for (Entry<Material, MaterialItem> material : component.getValue().entrySet()) {
				get(component.getKey(), material.getKey()).unify(material.getValue().getItem());
			}
		}
		// TODO other generated items
	}
}
