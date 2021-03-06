package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TitaniumMaterial extends MaterialMetal {

    @Inject
    protected TitaniumMaterial() {
        name = "Titanium";
        element = Optional.of(Element.Ti);
        color = 0xDCA0F0;
    }

}
