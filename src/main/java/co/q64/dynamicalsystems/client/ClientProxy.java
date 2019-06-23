package co.q64.dynamicalsystems.client;

import co.q64.dynamicalsystems.CommonProxy;
import co.q64.dynamicalsystems.client.loader.ClientLoader;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientProxy extends CommonProxy {
    protected @Inject ClientLoader clientLoader;

    protected @Inject ClientProxy() {}

    @Override
    public void initialize() {
        super.initialize();
        clientLoader.load();
    }
}
