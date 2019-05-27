package co.q64.dynamicalsystems.material;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.components.IngotComponent;

@Singleton
public class Components {
	public @Inject IngotComponent ingot;

	protected @Inject Components() {}

}
