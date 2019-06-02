package co.q64.dynamicalsystems.loader.component;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.ComponentLoader;
import co.q64.dynamicalsystems.material.components.IngotComponent;

@Singleton
public class CraftingComponentLoader extends ComponentLoader {
	protected @Inject CraftingComponentLoader() {}

	@Inject
	protected void init() {

		add(IngotComponent.class, (component, material) -> {
			recipes.addCrafting(
					component, material,
					component, material,
					component, material,
					component, material,
					component, material,
					component, material,
					component, material,
					component, material,
					component, material,
					components.block, material);
		});
	}
}