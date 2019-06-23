package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.util.EventSubscriber;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientResourceRegistryLoader extends EventSubscriber {
    protected @Inject ModelLoader loader;
    protected @Inject ColorHandlerLoader colorHandlerLoader;
    protected @Inject TextureLoader textureLoader;

    protected @Inject ClientResourceRegistryLoader() {}

    public void initialize() {
        ModelLoaderRegistry.registerLoader(loader);
    }

    @SubscribeEvent
    public void modelLoadEvent(ModelRegistryEvent event) {

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
        textureLoader.loadTextures(event.getMap());
    }
}
