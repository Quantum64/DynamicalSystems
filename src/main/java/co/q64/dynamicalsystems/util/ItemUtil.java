package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.machine.MachineLoader;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ItemUtil {
    protected @Inject MaterialItemLoader materialItemLoader;
    protected @Inject MachineLoader machineLoader;

    protected @Inject ItemUtil() {}

    public List<MaterialItem> getMaterialItems() {
        return materialItemLoader.getItems();
    }

    public List<MachineBlockItem> getMachineItems() {
        return machineLoader.getItems();
    }

    public Optional<MaterialItem> getMaterialItem(ResourceLocation identifier) {
        return Optional.ofNullable(materialItemLoader.getIdentifierCache().get(identifier));
    }
}
