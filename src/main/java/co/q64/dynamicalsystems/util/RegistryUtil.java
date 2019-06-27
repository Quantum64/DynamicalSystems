package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.block.BaseBlock;
import co.q64.dynamicalsystems.block.item.BaseBlockItem;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import lombok.Getter;
import net.minecraft.item.Item;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RegistryUtil {
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject Logger logger;

    private @Getter List<BaseBlockItem> blockItems = new ArrayList<>();
    private @Getter List<BaseItem> baseItems = new ArrayList<>();
    private @Getter List<BaseBlockItem> baseBlockItems = new ArrayList<>();
    private @Getter List<BaseBlock> blocks = new ArrayList<>();
    private @Getter List<Item> items = new ArrayList<>();

    protected @Inject RegistryUtil() {}

    public void registerItem(BaseItem item) {
        if (items.contains(item)) {
            logger.info("ITEM REGISTRY DUPLICATION: " + identifierUtil.getIdentifier(item).toString());
        }
        items.add(item);
        baseItems.add(item);
    }

    public void registerBlock(BaseBlockItem item) {
        items.add(item);
        blockItems.add(item);
        baseBlockItems.add(item);
        blocks.add(item.getBlock());
    }

    public void registerMaterial(MaterialItem item) {
        if (items.contains(item)) {
            logger.info("ITEM REGISTRY DUPLICATION (material): " + identifierUtil.getIdentifier(item).toString());
        }
        items.add(item.getBaseItem());
        if (item.isBlock()) {
            blocks.add(item.getBaseBlock());
            blockItems.add(item.getBlockItem());
        } else {
            baseItems.add((BaseItem) item.getBaseItem());
        }
    }
}
