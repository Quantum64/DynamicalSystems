package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ManganeseMaterial extends MaterialMetal {

    @Inject
    protected ManganeseMaterial() {
        name = "Manganese";
        element = Optional.of(Element.Mn);
        color = 0xEEEEEE;
    }

}
