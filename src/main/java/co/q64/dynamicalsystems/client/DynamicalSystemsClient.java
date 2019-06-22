package co.q64.dynamicalsystems.client;

import net.fabricmc.api.ClientModInitializer;

public class DynamicalSystemsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DaggerClientComponent.create().getLoader().load();
    }
}
