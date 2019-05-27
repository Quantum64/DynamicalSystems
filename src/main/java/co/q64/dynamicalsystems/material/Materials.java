package co.q64.dynamicalsystems.material;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.materials.IronMaterial;

@Singleton
public class Materials {
	protected @Inject Materials() {}

	public @Inject IronMaterial iron;
}
