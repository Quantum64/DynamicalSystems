package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class RubidiumMaterial extends MaterialMetal {

    @Inject
    protected RubidiumMaterial() {
        name = "Rubidium";
        element = Optional.of(Element.Rb);
        color = 0xF01E1E;
    }

}
