package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.link.LinkManager;
import co.q64.dynamicalsystems.listener.Listener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class CommonLoader {
    protected @Inject Set<Listener> listeners;
    protected @Inject LinkManager linkManager;
    protected @Inject UnificationLoader unificationLoader;
    protected @Inject MaterialProcessor materialProcessor;
    protected @Inject ServerDataGenerator serverDataGenerator;

    protected @Inject CommonLoader() {}

    public void load() {
        for (Listener listener : listeners) {
            MinecraftForge.EVENT_BUS.register(listener);
            FMLJavaModLoadingContext.get().getModEventBus().register(listener);
        }
        unificationLoader.load();
        linkManager.initializeLinks();
        materialProcessor.load();
        serverDataGenerator.generateData(); // must be last
    }
}
