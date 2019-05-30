package co.q64.dynamicalsystems.loader;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.link.LinkManager;
import co.q64.dynamicalsystems.material.MaterialItemLoader;

@Singleton
public class CommonLoader {
	protected @Inject MaterialItemLoader materialItems;
	protected @Inject LinkManager linkManager;

	protected @Inject CommonLoader() {}

	public void load() {
		materialItems.registerItems();
		linkManager.initializeLinks();
	}
}
