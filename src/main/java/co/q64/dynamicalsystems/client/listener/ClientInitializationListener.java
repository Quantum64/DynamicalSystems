package co.q64.dynamicalsystems.client.listener;

import co.q64.dynamicalsystems.listener.Listener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientInitializationListener implements Listener {
    protected @Inject ClientInitializationListener() {}

    @SubscribeEvent
    public void onClientInitialize(FMLCommonSetupEvent event) {

    }
}
