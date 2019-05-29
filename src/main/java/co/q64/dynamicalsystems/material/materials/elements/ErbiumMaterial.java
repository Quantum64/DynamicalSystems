package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ErbiumMaterial extends MaterialMetal {

    @Inject
    protected ErbiumMaterial() {
        name = "Erbium";
        element = Optional.of(Element.Er);
        color = 0xEEEEEE;
    }

}
