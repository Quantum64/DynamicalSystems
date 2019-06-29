package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.client.gui.screen.MachineScreenFactory;
import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import co.q64.dynamicalsystems.loader.CommonLoader;
import co.q64.dynamicalsystems.resource.GeneratedPackFinder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientLoader {
    protected @Inject CommonLoader commonLoader;
    protected @Inject ClientResourceGenerator clientResourceGenerator;
    protected @Inject GeneratedPackFinder generatedPackFinder;

    protected @Inject MachineContainerType machineContainerType;
    protected @Inject MachineScreenFactory machineScreenFactory;

    protected @Inject ClientLoader() {}

    public void load() {
        clientResourceGenerator.generateModels();

        ScreenManager.registerFactory(machineContainerType, machineScreenFactory::create);

        //TODO move this (where?)
        Minecraft.getInstance().getResourcePackList().addPackFinder(generatedPackFinder);
    }
}
