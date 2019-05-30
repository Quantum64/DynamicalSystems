package co.q64.dynamicalsystems.client.loader;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.loader.CommonLoader;

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
