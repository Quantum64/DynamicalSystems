package co.q64.dynamicalsystems.material.materials;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;

@Singleton
public class IronMaterial extends MaterialMetal {

	@Inject
	protected IronMaterial() {
		super();
	}
}
