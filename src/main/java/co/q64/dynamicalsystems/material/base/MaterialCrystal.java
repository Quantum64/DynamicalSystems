package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;

public abstract class MaterialCrystal extends MaterialSolid {
	public MaterialCrystal() {
		super(Arrays.asList(MaterialCrystal.class));
		textureOverrideFamily = "crystal";
	}
}
