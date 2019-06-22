package co.q64.dynamicalsystems.material.components.fluid.solvent;

import co.q64.dynamicalsystems.material.base.ComponentLiquid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SolventMethanolComponent extends ComponentLiquid {
    protected @Inject SolventMethanolComponent() {
        prefix = "Dissolved";
        name = "in Methanol";
        textureName = "liquid";
        generate = material -> true;
    }
}
