package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.loader.CommonLoader;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientLoader {
    protected @Inject CommonLoader commonLoader;
    protected @Inject TextureLoader textureLoader;
    protected @Inject ModelLoader modelLoader;

    protected @Inject ClientLoader() {}

    public void load() {
        commonLoader.load();
        modelLoader.loadModels();
        textureLoader.loadTextures();
    }
}
