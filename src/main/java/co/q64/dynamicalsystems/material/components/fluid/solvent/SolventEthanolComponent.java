package co.q64.dynamicalsystems.material.components.fluid.solvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class SolventEthanolComponent extends ComponentLiquid {
	protected @Inject SolventEthanolComponent() {
		prefix = "Dissolved";
		name = "in Ethanol";
		textureName = "liquid";
		generate = material -> true;
	}
}
