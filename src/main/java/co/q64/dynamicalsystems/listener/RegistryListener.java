package co.q64.dynamicalsystems.listener;

import co.q64.dynamicalsystems.tile.TileEntityTypes;
import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RegistryListener implements Listener {
    protected @Inject RegistryUtil registryUtil;
    protected @Inject TileEntityTypes tileEntityTypes;

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
        event.getRegistry().registerAll(tileEntityTypes.getTypes().toArray(new TileEntityType[0]));
    }
}
