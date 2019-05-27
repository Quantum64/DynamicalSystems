package co.q64.dynamicalsystems;

import co.q64.dynamicalsystems.binders.ConstantBinders.Author;
import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.binders.ConstantBinders.Name;
import co.q64.dynamicalsystems.binders.ConstantBinders.Version;
import dagger.Module;
import dagger.Provides;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

@Module
public interface CommonModule {
	// @formatter:off
	
	static @Provides FabricLoader provideFabricLoader() { return FabricLoader.getInstance(); }
	static @Provides ModContainer provideModContainer(FabricLoader loader, @ModId String modId) { return loader.getModContainer(modId).get(); }
	
	static @Provides @ModId String provideModId() { return ModInformation.ID; }
	static @Provides @Name String provideName() { return ModInformation.NAME; }
	static @Provides @Version String provideAuthor() { return ModInformation.VERSION; }
	static @Provides @Author String provideVersion() { return ModInformation.AUTHOR; }
	// @formatter:on
}
