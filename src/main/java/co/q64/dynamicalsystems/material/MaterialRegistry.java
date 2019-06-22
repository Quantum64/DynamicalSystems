package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class MaterialRegistry {
    protected @Inject Logger logger;

    private Map<Class<? extends Material>, List<Material>> types = new HashMap<>();
    private boolean frozen = false;

    protected @Inject MaterialRegistry() {}

    @SuppressWarnings("unchecked") // Impossible
    private <T extends Material> List<T> getList(Class<T> type) {
        List<T> result = (List<T>) types.get(type);
        if (result != null) {
            return result;
        }
        if (frozen) {
            logger.info("Missed type on call to getList for type '" + type.getSimpleName() + "'. Probably incorrect use of the material registry!");
        }
        result = new ArrayList<>(1);
        for (Material material : getList(Material.class)) {
            if (material.getClass().isAssignableFrom(type)) {
                result.add((T) material);
            }
        }
        types.put(type, (List<Material>) result);
        return result;
    }

    public List<? extends Material> getMaterials() {
        return getList(Material.class);
    }

    public <T extends Material> List<T> getMaterialsOfType(Class<T> type) {
        return getList(type);
    }

    public void register(Material material, List<Class<? extends Material>> materialTypes) {
        for (Class<? extends Material> type : materialTypes) {
            List<Material> materials = types.get(type);
            if (materials == null) {
                materials = new ArrayList<>();
                types.put(type, materials);
            }
            materials.add(material);
        }
    }
}
