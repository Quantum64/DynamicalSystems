package co.q64.dynamicalsystems.client.listener;

import co.q64.dynamicalsystems.client.loader.ColorHandlerLoader;
import co.q64.dynamicalsystems.client.loader.DynamicModelLoader;
import co.q64.dynamicalsystems.listener.Listener;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientRegistryListener implements Listener {
    protected @Inject ColorHandlerLoader colorHandlerLoader;
    protected @Inject DynamicModelLoader modelLoader;
    //protected @Inject TextureLoader textureLoader;

    protected @Inject ClientRegistryListener() {}

    @SubscribeEvent
    public void modelLoadEvent(ModelRegistryEvent event) {
    }

    @SubscribeEvent
    public void modelBakeEvent(ModelBakeEvent event) {
        modelLoader.loadModels(event);
    }

    @SubscribeEvent
    public void onBlockColorHandler(ColorHandlerEvent.Block event) {
        colorHandlerLoader.registerBlockColors(event.getBlockColors());
    }

    @SubscribeEvent
    public void onItemColorHandler(ColorHandlerEvent.Item event) {
        colorHandlerLoader.registerItemColors(event.getItemColors());
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        //textureLoader.loadTextures(event.getMap());
    }
}
