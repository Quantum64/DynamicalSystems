package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.loader.CommonLoader;
import co.q64.dynamicalsystems.resource.GeneratedPackFinder;
import net.minecraft.client.Minecraft;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientLoader {
    protected @Inject CommonLoader commonLoader;
    protected @Inject ClientResourceGenerator clientResourceGenerator;
    protected @Inject GeneratedPackFinder generatedPackFinder;

    protected @Inject ClientLoader() {}

    public void load() {
        clientResourceGenerator.generateModels();
        //TODO move this (where?)
        Minecraft.getInstance().getResourcePackList().addPackFinder(generatedPackFinder);
    }
}
