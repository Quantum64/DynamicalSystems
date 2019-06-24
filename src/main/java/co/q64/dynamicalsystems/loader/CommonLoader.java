package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.link.LinkManager;
import co.q64.dynamicalsystems.listener.Listener;
import co.q64.dynamicalsystems.listener.RegistryListener;
import co.q64.dynamicalsystems.machine.MachineLoader;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class CommonLoader {
    protected @Inject Set<Listener> listeners;
    protected @Inject RegistryListener registryLoader;
    protected @Inject MaterialItemLoader materialItems;
    protected @Inject LinkManager linkManager;
    protected @Inject UnificationLoader unificationLoader;
    protected @Inject MaterialLoader materialLoader;
    protected @Inject MachineLoader machineLoader;

    protected @Inject CommonLoader() {}

    public void load() {
        for (Listener listener : listeners) {
            MinecraftForge.EVENT_BUS.register(listener);
            FMLJavaModLoadingContext.get().getModEventBus().register(listener);
        }
        materialItems.registerItems();
        machineLoader.loadMachines();
        unificationLoader.load();
        linkManager.initializeLinks();
        materialLoader.load();
    }
}
