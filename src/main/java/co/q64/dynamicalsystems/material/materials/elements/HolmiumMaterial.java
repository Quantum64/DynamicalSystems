package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class HolmiumMaterial extends MaterialMetal {

    @Inject
    protected HolmiumMaterial() {
        name = "Holmium";
        element = Optional.of(Element.Ho);
        color = 0xFFFFFF;
    }

}
