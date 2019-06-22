package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.recipe.Recipes;
import lombok.Getter;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public abstract class ComponentLoader {
    private @Getter Map<Class<? extends Component>, ComponentHandler<? extends Component>> handlers = new HashMap<>();
    protected @Inject Materials materials;
    protected @Inject Components components;
    protected @Inject Recipes recipes;

    public <T extends Component> void add(Class<T> component, ComponentHandler<T> handler) {
        handlers.put(component, handler);
    }
}
