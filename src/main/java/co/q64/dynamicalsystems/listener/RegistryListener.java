package co.q64.dynamicalsystems.listener;

import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class RegistryListener implements Listener {
    protected @Inject RegistryUtil registryUtil;
    protected @Inject Set<TileEntityType<?>> tileEntityTypes;

    protected @Inject Set<ContainerType<?>> containerTypes;

    protected @Inject RegistryListener() {}

    @SubscribeEvent
    public void onBlockRegistry(Register<Block> event) {
        for (Block block : registryUtil.getBlocks()) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public void onItemRegistry(Register<Item> event) {
        System.out.println("DS REGISTER ITEM: " + registryUtil.getItems().size());
        for (Item item : registryUtil.getItems()) {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public void onTileEntityRegistry(Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(tileEntityTypes.toArray(new TileEntityType[0]));
    }

    @SubscribeEvent
    public void onContainerRegistry(Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(containerTypes.toArray(new ContainerType[0]));
    }
}
