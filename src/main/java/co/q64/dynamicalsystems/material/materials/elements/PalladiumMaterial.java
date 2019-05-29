package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PalladiumMaterial extends MaterialMetal {

    @Inject
    protected PalladiumMaterial() {
        name = "Palladium";
        element = Optional.of(Element.Pd);
        color = 0xCED0DD;
    }

}
