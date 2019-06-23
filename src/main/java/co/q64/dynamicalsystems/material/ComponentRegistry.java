package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ComponentRegistry {
    private List<Component> components = new ArrayList<>();
    private boolean frozen;

    protected @Inject ComponentRegistry() {}

    public void register(Component component) {
        if (frozen) {
            throw new IllegalStateException("It is too late to register additional components!");
        }
        if (components.contains(component)) {
            throw new IllegalStateException("Component already registered: " + component.getName());
        }
        components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public void freeze() {
        frozen = true;
    }
}
