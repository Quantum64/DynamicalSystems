package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.material.ComponentHandler;
import co.q64.dynamicalsystems.material.ComponentLoader;
import co.q64.dynamicalsystems.material.ComponentRegistry;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.material.MaterialRegistry;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map.Entry;
import java.util.Set;

@Singleton
public class MaterialLoader {
    protected @Inject ComponentRegistry componentRegistry;
    protected @Inject MaterialRegistry materialRegistry;
    protected @Inject MaterialItemLoader materialItemLoader;
    protected @Inject Set<ComponentLoader> componentLoaders;

    protected @Inject MaterialLoader() {}

    public void load() {
        for (ComponentLoader loader : componentLoaders) {
            for (Entry<Class<? extends Component>, ComponentHandler<? extends Component>> entry : loader.getHandlers().entrySet()) {
                Class<? extends Component> componentClass = entry.getKey();
                for (Component component : componentRegistry.getComponents()) {
                    if (componentClass.isAssignableFrom(component.getClass())) {
                        for (Material material : materialRegistry.getMaterials()) {
                            if (materialItemLoader.generated(component, material)) {
                                entry.getValue().handleUnchecked(component, material);
                            }
                        }
                    }
                }
            }
        }
    }
}
