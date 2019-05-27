package co.q64.dynamicalsystems;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.MaterialItemLoader;

@Singleton
public class CommonLoader {
	protected @Inject MaterialItemLoader materialItems;

	protected @Inject CommonLoader() {}

	public void load() {
		materialItems.registerItems();
	}
}
