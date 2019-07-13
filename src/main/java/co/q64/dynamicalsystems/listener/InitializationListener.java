package co.q64.dynamicalsystems.listener;

import co.q64.dynamicalsystems.net.PacketManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InitializationListener implements Listener {
    protected @Inject PacketManager packetManager;

    protected @Inject InitializationListener() {}

    @SubscribeEvent
    public void onInitialize(FMLCommonSetupEvent event) {
        packetManager.register();
    }
}
