package co.q64.dynamicalsystems.server;

import net.fabricmc.api.DedicatedServerModInitializer;

public class DynamicalSystemsServer implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		DaggerServerComponent.create().getLoader().load();
	}
}
