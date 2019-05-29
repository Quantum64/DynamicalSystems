package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class RubidiumMaterial extends MaterialMetal {

    @Inject
    protected RubidiumMaterial() {
        name = "Rubidium";
        element = Optional.of(Element.Rb);
        color = 0xF01E1E;
    }

}
