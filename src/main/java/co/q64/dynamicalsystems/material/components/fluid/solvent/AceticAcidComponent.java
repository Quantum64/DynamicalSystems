package co.q64.dynamicalsystems.material.components.fluid.solvent;

import co.q64.dynamicalsystems.material.base.ComponentLiquid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AceticAcidComponent extends ComponentLiquid {
    protected @Inject AceticAcidComponent() {
        prefix = "Dissolved";
        name = "in Acetic Acid";
        textureName = "liquid";
        generate = material -> true;
    }
}
