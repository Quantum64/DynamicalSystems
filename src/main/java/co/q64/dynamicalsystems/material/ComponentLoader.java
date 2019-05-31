package co.q64.dynamicalsystems.material;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.recipe.Recipes;
import lombok.Getter;

public abstract class ComponentLoader {
	private @Getter Map<Class<? extends Component>, ComponentHandler<? extends Component>> handlers = new HashMap<>();
	protected @Inject Materials materials;
	protected @Inject Components components;
	protected @Inject Recipes recipes;

	public <T extends Component> void add(Class<T> component, ComponentHandler<T> handler) {
		handlers.put(component, handler);
	}
}
