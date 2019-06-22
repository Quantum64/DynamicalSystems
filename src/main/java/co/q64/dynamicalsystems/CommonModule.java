package co.q64.dynamicalsystems;

import co.q64.dynamicalsystems.binders.ConstantBinders.Author;
import co.q64.dynamicalsystems.binders.ConstantBinders.MachinesItemGroup;
import co.q64.dynamicalsystems.binders.ConstantBinders.MaterialsItemGroup;
import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.binders.ConstantBinders.Name;
import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.binders.ConstantBinders.Version;
import co.q64.dynamicalsystems.binders.PropertyBinders.AlignX;
import co.q64.dynamicalsystems.binders.PropertyBinders.AlignY;
import co.q64.dynamicalsystems.binders.PropertyBinders.AlignZ;
import co.q64.dynamicalsystems.binders.PropertyBinders.Down;
import co.q64.dynamicalsystems.binders.PropertyBinders.East;
import co.q64.dynamicalsystems.binders.PropertyBinders.North;
import co.q64.dynamicalsystems.binders.PropertyBinders.South;
import co.q64.dynamicalsystems.binders.PropertyBinders.Up;
import co.q64.dynamicalsystems.binders.PropertyBinders.West;
import co.q64.dynamicalsystems.link.LinkInfo;
import co.q64.dynamicalsystems.link.cottonresources.CottonResourcesLinkInfo;
import co.q64.dynamicalsystems.loader.component.CraftingComponentLoader;
import co.q64.dynamicalsystems.material.ComponentLoader;
import co.q64.dynamicalsystems.material.components.ScrewComponent;
import co.q64.dynamicalsystems.material.materials.elements.GoldMaterial;
import co.q64.dynamicalsystems.unification.Unification;
import co.q64.dynamicalsystems.util.identifier.IdentifierUtil;
import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.BooleanProperty;

import javax.inject.Singleton;

@Module
public interface CommonModule {
    // @formatter:off

    @Binds @IntoSet ComponentLoader bindCraftingComponentLoader(CraftingComponentLoader craftingComponentLoader);

    @Binds @IntoSet LinkInfo bindCottonResourcesLink(CottonResourcesLinkInfo cottonResourcesLinkInfo);

    static @Provides FabricLoader provideFabricLoader() { return FabricLoader.getInstance(); }

    static @Provides ModContainer provideModContainer(FabricLoader loader, @ModId String modId) { return loader.getModContainer(modId).get(); }

    static @Provides @ModId String provideModId() { return ModInformation.ID; }

    static @Provides @Name String provideName() { return ModInformation.NAME; }

    static @Provides @Version String provideAuthor() { return ModInformation.VERSION; }

    static @Provides @Author String provideVersion() { return ModInformation.AUTHOR; }

    static @Provides @SharedNamespace String provideSharedNamespace() { return ModInformation.SHARED_NAMESPACE; }

    static @Provides @Singleton @MaterialsItemGroup ItemGroup provideMaterialsItemGroup(IdentifierUtil identifierUtil, Lazy<Unification> unification, Lazy<GoldMaterial> gold, Lazy<ScrewComponent> screw) {
        return FabricItemGroupBuilder.create(identifierUtil.get("materials")).icon(() -> unification.get().getStack(screw.get(), gold.get()).getItemStack()).build();
    }

    static @Provides @Singleton @MachinesItemGroup ItemGroup provideMachinesItemGroup(IdentifierUtil identifierUtil, Lazy<Unification> unification, Lazy<GoldMaterial> gold, Lazy<ScrewComponent> screw) {
        return FabricItemGroupBuilder.create(identifierUtil.get("machines")).icon(() -> unification.get().getStack(screw.get(), gold.get()).getItemStack()).build();
    }

    static @Provides @Singleton @Up BooleanProperty provideUp() { return BooleanProperty.of("up"); }

    static @Provides @Singleton @Down BooleanProperty provideDown() { return BooleanProperty.of("down"); }

    static @Provides @Singleton @North BooleanProperty provideNorth() { return BooleanProperty.of("north"); }

    static @Provides @Singleton @South BooleanProperty provideSouth() { return BooleanProperty.of("south"); }

    static @Provides @Singleton @East BooleanProperty provideEast() { return BooleanProperty.of("east"); }

    static @Provides @Singleton @West BooleanProperty provideWest() { return BooleanProperty.of("west"); }

    static @Provides @Singleton @AlignX BooleanProperty provideAlignX() { return BooleanProperty.of("alignx"); }

    static @Provides @Singleton @AlignY BooleanProperty provideAlignY() { return BooleanProperty.of("aligny"); }

    static @Provides @Singleton @AlignZ BooleanProperty provideAlignZ() { return BooleanProperty.of("alignz"); }
    // @formatter:on
}
