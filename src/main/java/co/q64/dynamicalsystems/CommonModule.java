package co.q64.dynamicalsystems;

import co.q64.dynamicalsystems.binders.ConstantBinders.Author;
import co.q64.dynamicalsystems.binders.ConstantBinders.ConfigFolder;
import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.binders.ConstantBinders.Name;
import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.binders.ConstantBinders.Version;
import co.q64.dynamicalsystems.binders.PropertyBinders.Down;
import co.q64.dynamicalsystems.binders.PropertyBinders.East;
import co.q64.dynamicalsystems.binders.PropertyBinders.North;
import co.q64.dynamicalsystems.binders.PropertyBinders.Running;
import co.q64.dynamicalsystems.binders.PropertyBinders.South;
import co.q64.dynamicalsystems.binders.PropertyBinders.Up;
import co.q64.dynamicalsystems.binders.PropertyBinders.West;
import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import co.q64.dynamicalsystems.link.LinkInfo;
import co.q64.dynamicalsystems.link.cottonresources.CottonResourcesLinkInfo;
import co.q64.dynamicalsystems.listener.Listener;
import co.q64.dynamicalsystems.listener.RegistryListener;
import co.q64.dynamicalsystems.listener.ServerStartListener;
import co.q64.dynamicalsystems.loader.component.CraftingComponentLoader;
import co.q64.dynamicalsystems.material.ComponentLoader;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.state.BooleanProperty;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;
import java.io.File;

@Module
public interface CommonModule {
    // @formatter:off
    @Binds @IntoSet ComponentLoader bindCraftingComponentLoader(CraftingComponentLoader craftingComponentLoader);
    @Binds @IntoSet LinkInfo bindCottonResourcesLink(CottonResourcesLinkInfo cottonResourcesLinkInfo);

    @Binds @IntoSet Listener bindRegistryListener(RegistryListener serverStartListener);
    @Binds @IntoSet Listener bindServerStartListener(ServerStartListener serverStartListener);

    @Binds @IntoSet ContainerType<?> bindMachineContainerType(MachineContainerType type);

    static @Provides @Singleton FMLJavaModLoadingContext provideFMLModLoadingContext() { return FMLJavaModLoadingContext.get(); }
    static @Provides @ConfigFolder @Singleton File provideConfigFolder(@ModId String modId) { return new File(FMLPaths.CONFIGDIR.get().toFile(), modId); }
    static @Provides @Singleton Logger provideLogger() { return LogManager.getLogger(); }

    static @Provides @ModId String provideModId() { return ModInformation.ID; }
    static @Provides @Name String provideName() { return ModInformation.NAME; }
    static @Provides @Version String provideAuthor() { return ModInformation.VERSION; }
    static @Provides @Author String provideVersion() { return ModInformation.AUTHOR; }
    static @Provides @SharedNamespace String provideSharedNamespace() { return ModInformation.SHARED_NAMESPACE; }

    static @Provides @Singleton @Up BooleanProperty provideUp() { return BooleanProperty.create("up"); }
    static @Provides @Singleton @Down BooleanProperty provideDown() { return BooleanProperty.create("down"); }
    static @Provides @Singleton @North BooleanProperty provideNorth() { return BooleanProperty.create("north"); }
    static @Provides @Singleton @South BooleanProperty provideSouth() { return BooleanProperty.create("south"); }
    static @Provides @Singleton @East BooleanProperty provideEast() { return BooleanProperty.create("east"); }
    static @Provides @Singleton @West BooleanProperty provideWest() { return BooleanProperty.create("west"); }

    static @Provides @Singleton @Running BooleanProperty provideRunning() { return BooleanProperty.create("running"); }
    // @formatter:on
}
