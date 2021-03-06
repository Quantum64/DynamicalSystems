package co.q64.dynamicalsystems.client.listener;

import co.q64.dynamicalsystems.client.gui.screen.MachineScreenFactory;
import co.q64.dynamicalsystems.client.loader.ClientResourceGenerator;
import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import co.q64.dynamicalsystems.listener.Listener;
import co.q64.dynamicalsystems.loader.CommonLoader;
import co.q64.dynamicalsystems.resource.GeneratedPackFinder;
import co.q64.dynamicalsystems.resource.Translations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientInitializationListener implements Listener {
    protected @Inject Translations translations;
    protected @Inject ClientResourceGenerator clientResourceGenerator;
    protected @Inject GeneratedPackFinder generatedPackFinder;

    protected @Inject MachineContainerType machineContainerType;
    protected @Inject MachineScreenFactory machineScreenFactory;

    protected @Inject ClientInitializationListener() {}

    @SubscribeEvent
    public void onClientInitialize(FMLCommonSetupEvent event) {
        translations.init();
        clientResourceGenerator.generateModels();
        ScreenManager.registerFactory(machineContainerType, machineScreenFactory::create);
        Minecraft.getInstance().getResourcePackList().addPackFinder(generatedPackFinder);
    }
}
