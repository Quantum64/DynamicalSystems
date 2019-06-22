package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ComponentRegistry {
    private List<Component> components = new ArrayList<>();

    protected @Inject ComponentRegistry() {}

    public void register(Component component) {
        if (components.contains(component)) {
            throw new IllegalStateException("Component already registered: " + component.getName());
        }
        components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }
}
