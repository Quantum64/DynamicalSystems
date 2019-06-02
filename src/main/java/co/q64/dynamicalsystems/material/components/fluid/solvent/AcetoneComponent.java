package co.q64.dynamicalsystems.material.components.fluid.solvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class AcetoneComponent extends ComponentLiquid {
	protected @Inject AcetoneComponent() {
		prefix = "Dissolved";
		name = "in Acetone";
		textureName = "liquid";
		generate = material -> true;
	}
}
