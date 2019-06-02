package co.q64.dynamicalsystems.material.components.fluid.solvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class IsopropanolComponent extends ComponentLiquid {
	protected @Inject IsopropanolComponent() {
		prefix = "Dissolved";
		name = "in Acetic Acid";
		textureName = "liquid";
		generate = material -> true;
	}
}
