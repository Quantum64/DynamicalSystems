package co.q64.dynamicalsystems.client;

import co.q64.dynamicalsystems.CommonComponent;
import co.q64.dynamicalsystems.CommonModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CommonModule.class, ClientModule.class})
public interface ClientComponent extends CommonComponent {
    public ClientProxy getProxy();
}
