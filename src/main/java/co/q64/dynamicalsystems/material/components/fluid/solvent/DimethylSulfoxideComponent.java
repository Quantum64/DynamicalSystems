package co.q64.dynamicalsystems.material.components.fluid.solvent;

import co.q64.dynamicalsystems.material.base.ComponentLiquid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DimethylSulfoxideComponent extends ComponentLiquid {
    protected @Inject DimethylSulfoxideComponent() {
        prefix = "Dissolved";
        name = "in Dimethyl Sulfoxide";
        textureName = "liquid";
        generate = material -> true;
    }
}
