package co.q64.dynamicalsystems;

import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.Author;
import co.q64.dynamicalsystems.binders.ConstantBinders.MaterialsItemGroup;
import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.binders.ConstantBinders.Name;
import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.binders.ConstantBinders.Version;
import co.q64.dynamicalsystems.link.LinkInfo;
import co.q64.dynamicalsystems.link.cottonresources.CottonResourcesLinkInfo;
import co.q64.dynamicalsystems.material.components.ScrewComponent;
import co.q64.dynamicalsystems.material.materials.elements.GoldMaterial;
import co.q64.dynamicalsystems.unification.Unification;
import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

@Module
public interface CommonModule {
	// @formatter:off
	
	@Binds @IntoSet LinkInfo bindCottonResourcesLink(CottonResourcesLinkInfo cottonResourcesLinkInfo);
	
	static @Provides FabricLoader provideFabricLoader() { return FabricLoader.getInstance(); }
	static @Provides ModContainer provideModContainer(FabricLoader loader, @ModId String modId) { return loader.getModContainer(modId).get(); }
	
	static @Provides @ModId String provideModId() { return ModInformation.ID; }
	static @Provides @Name String provideName() { return ModInformation.NAME; }
	static @Provides @Version String provideAuthor() { return ModInformation.VERSION; }
	static @Provides @Author String provideVersion() { return ModInformation.AUTHOR; }
	static @Provides @SharedNamespace String provideSharedNamespace() { return ModInformation.SHARED_NAMESPACE; }
	
	static @Provides @Singleton @MaterialsItemGroup ItemGroup provideMaterialsItemGroup(@ModId String modId, Lazy<Unification> unification, Lazy<GoldMaterial> gold, Lazy<ScrewComponent> screw) {
		return FabricItemGroupBuilder.create(new Identifier(modId, "materials")).icon(() -> unification.get().getMaterialStack(screw.get(), gold.get())).build();
	}
	// @formatter:on
}
