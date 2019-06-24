package co.q64.dynamicalsystems.listener;

import co.q64.dynamicalsystems.resource.GeneratedPackFinder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServerStartListener implements Listener {
    protected @Inject GeneratedPackFinder generatedPackFinder;

    protected @Inject ServerStartListener() {}

    @SubscribeEvent
    public void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        event.getServer().getResourcePacks().addPackFinder(generatedPackFinder);
    }
}
