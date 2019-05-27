package co.q64.dynamicalsystems.client;

import javax.inject.Singleton;

import co.q64.dynamicalsystems.CommonModule;
import dagger.Component;

@Singleton
@Component(modules = { CommonModule.class })
public interface ClientComponent {
	public ClientLoader getLoader();
}
