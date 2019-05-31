package co.q64.dynamicalsystems.client;

import javax.inject.Singleton;

import co.q64.dynamicalsystems.CommonModule;
import co.q64.dynamicalsystems.client.loader.ClientLoader;
import dagger.Component;

@Singleton
@Component(modules = { CommonModule.class, ClientModule.class })
public interface ClientComponent {
	public ClientLoader getLoader();
}
