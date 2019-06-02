package co.q64.dynamicalsystems.material.components.fluid;

import javax.inject.Inject;
import javax.inject.Singleton;
import co.q64.dynamicalsystems.material.base.ComponentLiquid;

@Singleton
public class LiquidComponent extends ComponentLiquid {
	protected @Inject LiquidComponent() {
		textureName = "liquid";
		generate = material -> true;
	}
}
