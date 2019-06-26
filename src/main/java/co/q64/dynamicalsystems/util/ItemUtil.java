package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.block.extra.BlockDefinition;
import co.q64.dynamicalsystems.block.extra.ExtraBlockLoader;
import co.q64.dynamicalsystems.block.item.ExtraBlockItem;
import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.machine.MachineProcessor;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
public class ItemUtil {
    protected @Inject MaterialItemLoader materialItemLoader;
    protected @Inject MachineProcessor machineProcessor;
    protected @Inject ExtraBlockLoader extraBlockLoader;

    protected @Inject ItemUtil() {}

    public List<MaterialItem> getMaterialItems() {
        return materialItemLoader.getItems();
    }

    public List<MachineBlockItem> getMachineItems() {
        return machineProcessor.getItems();
    }

    public Optional<MaterialItem> getMaterialItem(ResourceLocation identifier) {
        return Optional.ofNullable(materialItemLoader.getIdentifierCache().get(identifier));
    }

    public ExtraBlockItem getExtraBlockItem(BlockDefinition definition) {
        return extraBlockLoader.getBlocks().get(definition);
    }

    public Collection<ExtraBlockItem> getExtraBlockItems() {
        return extraBlockLoader.getBlocks().values();
    }
}
