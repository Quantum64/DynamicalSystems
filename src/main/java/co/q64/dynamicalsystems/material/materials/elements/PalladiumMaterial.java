package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class PalladiumMaterial extends MaterialMetal {

    @Inject
    protected PalladiumMaterial() {
        name = "Palladium";
        element = Optional.of(Element.Pd);
        color = 0xCED0DD;
    }

}
