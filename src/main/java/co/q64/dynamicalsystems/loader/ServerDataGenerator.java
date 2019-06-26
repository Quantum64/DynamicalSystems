package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.resource.ResourcePackGenerator;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.block.Block;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServerDataGenerator {
    protected @Inject ResourcePackGenerator packGenerator;
    protected @Inject RegistryUtil registryUtil;
    protected @Inject IdentifierUtil identifiers;

    protected @Inject ServerDataGenerator() {}

    public void generateData() {
        for (Block block : registryUtil.getBlocks()) {
            packGenerator.writeLootTable(block.getRegistryName().getPath(), block.getRegistryName());
        }
    }
}
