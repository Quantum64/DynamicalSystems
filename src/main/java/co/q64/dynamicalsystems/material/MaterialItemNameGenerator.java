package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MaterialItemNameGenerator {
    public @Inject MaterialItemNameGenerator() {}

    public String generate(Component component, Material material) {
        return component.getPrefix() + (component.getPrefix().isEmpty() ? "" : " ") + material.getName() + (component.getName().isEmpty() ? "" : " ") + component.getName();
    }

    public String generateId(Component component, Material material) {
        return generate(component, material).replace(" ", "_").toLowerCase();
    }
}
