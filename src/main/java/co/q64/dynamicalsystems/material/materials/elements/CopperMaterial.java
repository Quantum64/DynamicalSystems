package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CopperMaterial extends MaterialMetal {

    @Inject
    protected CopperMaterial() {
        name = "Copper";
        element = Optional.of(Element.Cu);
        color = 0xFF8000;
    }

}
