package co.q64.dynamicalsystems.listener;

import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RegistryListener implements Listener {
    protected @Inject RegistryUtil registryUtil;

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
}
