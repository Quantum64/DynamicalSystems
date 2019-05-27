package co.q64.dynamicalsystems.inject;

import javax.inject.Singleton;

import co.q64.dynamicalsystems.DynamicalModule;
import co.q64.dynamicalsystems.Loader;
import dagger.Component;

@Singleton
@Component(modules = { DynamicalModule.class })
public interface DynamicalComponent {
	public Loader getLoader();
}
