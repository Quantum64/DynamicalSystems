package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class LithiumMaterial extends MaterialMetal {

    @Inject
    protected LithiumMaterial() {
        name = "Lithium";
        element = Optional.of(Element.Li);
        color = 0xCBCBCB;
    }

}
