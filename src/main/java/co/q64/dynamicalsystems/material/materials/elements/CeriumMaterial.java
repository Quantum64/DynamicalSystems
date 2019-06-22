package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CeriumMaterial extends MaterialMetal {

    @Inject
    protected CeriumMaterial() {
        name = "Cerium";
        element = Optional.of(Element.Ce);
        color = 0xEEEEEE;
    }

}
