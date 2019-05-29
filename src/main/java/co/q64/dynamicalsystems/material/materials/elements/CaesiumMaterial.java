package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CaesiumMaterial extends MaterialMetal {

    @Inject
    protected CaesiumMaterial() {
        name = "Caesium";
        element = Optional.of(Element.Cs);
        color = 0xFFFFFC;
    }

}
