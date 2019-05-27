package co.q64.dynamicalsystems.client;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.CommonLoader;

@Singleton
public class ClientLoader {
	protected @Inject CommonLoader commonLoader;

	protected @Inject ClientLoader() {}

	public void load() {
		commonLoader.load();
	}
}
