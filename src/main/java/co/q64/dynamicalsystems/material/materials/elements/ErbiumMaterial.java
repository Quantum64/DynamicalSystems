package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ErbiumMaterial extends MaterialMetal {

    @Inject
    protected ErbiumMaterial() {
        name = "Erbium";
        element = Optional.of(Element.Er);
        color = 0xEEEEEE;
    }

}
