package co.q64.dynamicalsystems.server;

import co.q64.dynamicalsystems.CommonModule;
import co.q64.dynamicalsystems.loader.CommonLoader;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CommonModule.class})
public interface ServerComponent {
    public CommonLoader getLoader();
}
