package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class GalliumMaterial extends MaterialMetal {

    @Inject
    protected GalliumMaterial() {
        name = "Gallium";
        element = Optional.of(Element.Ga);
        color = 0xEEEEFF;
    }

}
