package co.q64.dynamicalsystems.block.extra;

import co.q64.dynamicalsystems.block.item.ExtraBlockItem;
import co.q64.dynamicalsystems.block.item.ExtraBlockItemFactory;
import co.q64.dynamicalsystems.util.RegistryUtil;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ExtraBlockLoader {
    protected @Inject ExtraBlocks extraBlocks;
    protected @Inject ExtraBlockRegistry registry;
    protected @Inject RegistryUtil registryUtil;
    protected @Inject ExtraBlockItemFactory extraBlockItemFactory;

    private @Getter Map<BlockDefinition, ExtraBlockItem> blocks = new HashMap<>();

    protected @Inject ExtraBlockLoader() {}

    public void loadExtraBlocks() {
        for (BlockDefinition definition : registry.getBlocks()) {
            ExtraBlockItem block = extraBlockItemFactory.create(definition);
            blocks.put(definition, block);
            registryUtil.registerBlock(block);
        }
    }
}
