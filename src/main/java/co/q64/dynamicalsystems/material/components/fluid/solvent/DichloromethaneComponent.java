package co.q64.dynamicalsystems.material.components.fluid.solvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class DichloromethaneComponent extends ComponentLiquid {
	protected @Inject DichloromethaneComponent() {
		prefix = "Dissolved";
		name = "in Dichloromethane";
		textureName = "liquid";
		generate = material -> true;
	}
}
