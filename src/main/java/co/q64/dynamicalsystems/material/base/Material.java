package co.q64.dynamicalsystems.material.base;

import javax.inject.Inject;

import co.q64.dynamicalsystems.material.MaterialRegistry;

public abstract class Material {
	protected @Inject MaterialRegistry registry;

	public Material() {

	}
}
