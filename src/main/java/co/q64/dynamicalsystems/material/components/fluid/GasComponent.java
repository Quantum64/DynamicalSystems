package co.q64.dynamicalsystems.material.components.fluid;

import co.q64.dynamicalsystems.material.base.ComponentLiquid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GasComponent extends ComponentLiquid {
    protected @Inject GasComponent() {
        textureName = "liquid";
        generate = material -> true;
    }
}
