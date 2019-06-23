package co.q64.dynamicalsystems.server;

import co.q64.dynamicalsystems.CommonProxy;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServerProxy extends CommonProxy {
    protected @Inject ServerProxy() {}

    @Override
    public void initialize() {
        super.initialize();
    }
}
