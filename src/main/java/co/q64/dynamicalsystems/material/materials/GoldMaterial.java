package co.q64.dynamicalsystems.material.materials;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;

@Singleton
public class GoldMaterial extends MaterialMetal {

	@Inject
	protected GoldMaterial() {
		name = "Gold";
		color = 0xFFFF00;
	}

}
