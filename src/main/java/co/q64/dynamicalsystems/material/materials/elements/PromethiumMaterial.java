package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PromethiumMaterial extends MaterialMetal {

    @Inject
    protected PromethiumMaterial() {
        name = "Promethium";
        element = Optional.of(Element.Pm);
        color = 0xFFFFFF;
    }

}
