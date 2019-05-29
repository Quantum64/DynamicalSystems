package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;

public abstract class MaterialGas extends MaterialFluid {
	public MaterialGas() {
		super(Arrays.asList(MaterialGas.class));
	}
}
