package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class GalliumMaterial extends MaterialMetal {

    @Inject
    protected GalliumMaterial() {
        name = "Gallium";
        element = Optional.of(Element.Ga);
        color = 0xEEEEFF;
    }

}
