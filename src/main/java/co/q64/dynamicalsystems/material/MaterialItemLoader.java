package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.item.SimpleMaterialItemFactory;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.RegistryUtil;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class MaterialItemLoader {
    protected @Inject Materials materials;
    protected @Inject Components components;
    protected @Inject MaterialRegistry materialRegistry;
    protected @Inject ComponentRegistry componentRegistry;
    protected @Inject SimpleMaterialItemFactory materialItemFactory;
    protected @Inject RegistryUtil registryUtil;
    protected @Inject IdentifierUtil identifierUtil;

    private @Getter List<MaterialItem> items = new ArrayList<>();
    private @Getter Map<Component, Map<Material, MaterialItem>> itemMap = new HashMap<>();
    private @Getter Map<ResourceLocation, MaterialItem> identifierCache = new HashMap<>();

    protected @Inject MaterialItemLoader() {}

    @Inject
    protected void registerItems() {
        for (Component component : componentRegistry.getComponents()) {
            for (Material material : materialRegistry.getMaterials()) {
                if (component.getGenerate().test(material)) {
                    MaterialItem item = component.getFactory().orElse(m -> materialItemFactory.create(component, m)).apply(material);
                    items.add(item);
                    Map<Material, MaterialItem> materialMap = itemMap.get(component);
                    if (materialMap == null) {
                        materialMap = new HashMap<>();
                        itemMap.put(component, materialMap);
                    }
                    materialMap.put(material, item);
                    identifierCache.put(identifierUtil.getIdentifier(item), item);
                    registryUtil.registerMaterial(item);
                }
            }
        }
        components.freeze();
    }

    // Generate all items with recipes
    public boolean generated(Component component, Material material) {
        return itemMap.containsKey(component) && itemMap.get(component).containsKey(material);
    }

    public Optional<MaterialItem> getItem(Component component, Material material) {
        return generated(component, material) ? Optional.of(itemMap.get(component).get(material)) : Optional.empty();
    }
}
