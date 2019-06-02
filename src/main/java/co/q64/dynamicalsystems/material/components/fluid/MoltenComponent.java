package co.q64.dynamicalsystems.material.components.fluid;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class MoltenComponent extends ComponentLiquid {
	protected @Inject MoltenComponent() {
		prefix = "Moletn";
		textureName = "liquid";
		generate = material -> true;
	}
}
