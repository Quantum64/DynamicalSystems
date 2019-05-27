package co.q64.dynamicalsystems;

import co.q64.dynamicalsystems.inject.DaggerDynamicalComponent;
import co.q64.dynamicalsystems.inject.DynamicalComponent;
import net.fabricmc.api.ModInitializer;

public class DynamicalSystems implements ModInitializer {

	@Override
	public void onInitialize() {
		DynamicalComponent component = DaggerDynamicalComponent.create();
		component.getLoader().getClass();
	}
}
