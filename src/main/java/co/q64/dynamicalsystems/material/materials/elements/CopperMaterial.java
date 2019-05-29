package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CopperMaterial extends MaterialMetal {

    @Inject
    protected CopperMaterial() {
        name = "Copper";
        element = Optional.of(Element.Cu);
        color = 0xFF8000;
    }

}
