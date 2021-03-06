package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CaesiumMaterial extends MaterialMetal {

    @Inject
    protected CaesiumMaterial() {
        name = "Caesium";
        element = Optional.of(Element.Cs);
        color = 0xFFFFFC;
    }

}
