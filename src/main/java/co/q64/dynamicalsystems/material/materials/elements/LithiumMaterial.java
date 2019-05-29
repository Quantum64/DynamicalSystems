package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class LithiumMaterial extends MaterialMetal {

    @Inject
    protected LithiumMaterial() {
        name = "Lithium";
        element = Optional.of(Element.Li);
        color = 0xCBCBCB;
    }

}
