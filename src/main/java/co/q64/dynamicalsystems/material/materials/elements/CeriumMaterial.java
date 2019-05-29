package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CeriumMaterial extends MaterialMetal {

    @Inject
    protected CeriumMaterial() {
        name = "Cerium";
        element = Optional.of(Element.Ce);
        color = 0xEEEEEE;
    }

}
