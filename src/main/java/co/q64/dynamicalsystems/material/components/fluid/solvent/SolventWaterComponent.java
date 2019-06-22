package co.q64.dynamicalsystems.material.components.fluid.solvent;

import co.q64.dynamicalsystems.material.base.ComponentLiquid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SolventWaterComponent extends ComponentLiquid {
    protected @Inject SolventWaterComponent() {
        prefix = "Dissolved";
        textureName = "liquid";
        generate = material -> true;
    }
}
