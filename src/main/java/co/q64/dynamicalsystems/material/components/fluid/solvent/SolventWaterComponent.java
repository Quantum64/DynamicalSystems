package co.q64.dynamicalsystems.material.components.fluid.solvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class SolventWaterComponent extends ComponentLiquid {
	protected @Inject SolventWaterComponent() {
		prefix = "Dissolved";
		textureName = "liquid";
		generate = material -> true;
	}
}
