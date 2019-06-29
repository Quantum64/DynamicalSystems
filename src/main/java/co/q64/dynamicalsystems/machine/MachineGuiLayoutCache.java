package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.recipe.RecipeType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MachineGuiLayoutCache {
    protected @Inject co.q64.dynamicalsystems.machine.MachineGuiLayoutFactory layoutFactory;
    private Map<RecipeType, MachineGuiLayout> cache = new HashMap<>();

    protected @Inject MachineGuiLayoutCache() {}

    public MachineGuiLayout get(RecipeType type) {
        return cache.computeIfAbsent(type, t -> layoutFactory.create(t));
    }
}
