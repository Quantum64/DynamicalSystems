package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class DysprosiumMaterial extends MaterialMetal {

    @Inject
    protected DysprosiumMaterial() {
        name = "Dysprosium";
        element = Optional.of(Element.Dy);
        color = 0xFFFFEE;
    }

}
