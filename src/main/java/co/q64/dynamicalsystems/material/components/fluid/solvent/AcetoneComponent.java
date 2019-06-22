package co.q64.dynamicalsystems.material.components.fluid.solvent;

import co.q64.dynamicalsystems.material.base.ComponentLiquid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AcetoneComponent extends ComponentLiquid {
    protected @Inject AcetoneComponent() {
        prefix = "Dissolved";
        name = "in Acetone";
        textureName = "liquid";
        generate = material -> true;
    }
}
