package co.q64.dynamicalsystems.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;

public class DynamicalSystemsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		DaggerClientComponent.create().getLoader().load();
	}
}
