package co.q64.dynamicalsystems.client;

import co.q64.dynamicalsystems.client.model.CableModel;
import co.q64.dynamicalsystems.client.model.CustomModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;

@Module
public interface ClientModule {
	// @formatter:off
	
	@Binds @IntoSet CustomModel bindCableUnbakedModel(CableModel cableModel);
	
	static @Provides ModelLoadingRegistry provideModelLoadingRegistry() { return ModelLoadingRegistry.INSTANCE; }
	// @formatter:on
}
