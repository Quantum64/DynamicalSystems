package co.q64.dynamicalsystems.unification;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import net.minecraft.item.ItemStack;

@Singleton
public class Unification {
	protected @Inject MaterialItemLoader materialItemLoader;

	protected @Inject Unification() {}

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

}
