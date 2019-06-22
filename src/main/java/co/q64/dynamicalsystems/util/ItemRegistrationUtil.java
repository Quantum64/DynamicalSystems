package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class ItemRegistrationUtil {
    protected @Inject ItemIdentifierUtil identifierUtil;
    protected @Inject Logger logger;

    private Set<Identifier> registered = new HashSet<>();

    protected @Inject ItemRegistrationUtil() {}

    public void registerItem(BaseItem item) {
        Identifier id = identifierUtil.getIdentifier(item);
        if (registered.contains(id)) {
            logger.info("ITEM REGISTRY DUPLICATION: " + id.toString());
        }
        registered.add(id);
        Registry.register(Registry.ITEM, id, item);
    }

    public void registerMaterial(MaterialItem item) {
        Identifier id = identifierUtil.getIdentifier(item);
        if (registered.contains(id)) {
            logger.info("ITEM REGISTRY DUPLICATION (material): " + id.toString());
        }
        registered.add(id);
        Registry.register(Registry.ITEM, id, item.getItem());
        if (item.isBlock()) {
            Registry.register(Registry.BLOCK, id, item.getBlock());
        }
    }
}
