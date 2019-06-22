package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.link.LinkManager;
import co.q64.dynamicalsystems.material.MaterialItemLoader;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommonLoader {
    protected @Inject MaterialItemLoader materialItems;
    protected @Inject LinkManager linkManager;
    protected @Inject UnificationLoader unificationLoader;
    protected @Inject MaterialLoader materialLoader;

    protected @Inject CommonLoader() {}

    public void load() {
        materialItems.registerItems();
        unificationLoader.load();
        linkManager.initializeLinks();
        materialLoader.load();
    }
}
