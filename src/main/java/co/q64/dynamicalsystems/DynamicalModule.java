package co.q64.dynamicalsystems;

import co.q64.dynamicalsystems.inject.ConstantBinders.ModId;
import dagger.Module;
import dagger.Provides;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

@Module
public interface DynamicalModule {
	// @formatter:off
	
	static @Provides FabricLoader provideFabricLoader() { return FabricLoader.getInstance(); }
	static @Provides ModContainer provideModContainer(FabricLoader loader, @ModId String modId) { return loader.getModContainer(modId).get(); }
	// @formatter:on
}
