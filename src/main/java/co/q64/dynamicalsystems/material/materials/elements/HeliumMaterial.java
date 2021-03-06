package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class HeliumMaterial extends MaterialMetal {

    @Inject
    protected HeliumMaterial() {
        name = "Helium";
        element = Optional.of(Element.He);
        color = 0xDDDD00;
    }

}
