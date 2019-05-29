package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ScandiumMaterial extends MaterialMetal {

    @Inject
    protected ScandiumMaterial() {
        name = "Scandium";
        element = Optional.of(Element.Sc);
        color = 0xFFFFFF;
    }

}
