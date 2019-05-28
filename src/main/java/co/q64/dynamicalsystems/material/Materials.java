package co.q64.dynamicalsystems.material;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.materials.GoldMaterial;
import co.q64.dynamicalsystems.material.materials.IronMaterial;

@Singleton
public class Materials {
	public @Inject IronMaterial iron;
	public @Inject GoldMaterial gold;

	protected @Inject Materials() {}

}
