package co.q64.dynamicalsystems.server;

import javax.inject.Singleton;

import co.q64.dynamicalsystems.CommonModule;
import co.q64.dynamicalsystems.CommonLoader;
import dagger.Component;

@Singleton
@Component(modules = { CommonModule.class })
public interface ServerComponent {
	public CommonLoader getLoader();
}
