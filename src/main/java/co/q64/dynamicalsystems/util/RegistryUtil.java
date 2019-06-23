package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class RegistryUtil {
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject Logger logger;

    private Set<ResourceLocation> registered = new HashSet<>();

    private @Getter Set<Block> blocks = new HashSet<>();
    private @Getter Set<Item> items = new HashSet<>();

    protected @Inject RegistryUtil() {}

    public void registerItem(BaseItem item) {
        ResourceLocation id = identifierUtil.getIdentifier(item);
        if (registered.contains(id)) {
            logger.info("ITEM REGISTRY DUPLICATION: " + id.toString());
        }
        registered.add(id);
        items.add(item);
    }

    public void registerMaterial(MaterialItem item) {
        ResourceLocation id = identifierUtil.getIdentifier(item);
        if (registered.contains(id)) {
            logger.info("ITEM REGISTRY DUPLICATION (material): " + id.toString());
        }
        registered.add(id);
        items.add(item.getBaseItem());
        if (item.isBlock()) {
            blocks.add(item.getBaseBlock());
        }
    }
}
