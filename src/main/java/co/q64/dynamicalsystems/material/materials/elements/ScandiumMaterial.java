package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ScandiumMaterial extends MaterialMetal {

    @Inject
    protected ScandiumMaterial() {
        name = "Scandium";
        element = Optional.of(Element.Sc);
        color = 0xFFFFFF;
    }

}
