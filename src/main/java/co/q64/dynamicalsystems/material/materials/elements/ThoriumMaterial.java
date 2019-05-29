package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ThoriumMaterial extends MaterialMetal {

    @Inject
    protected ThoriumMaterial() {
        name = "Thorium";
        element = Optional.of(Element.Th);
        color = 0x001E00;
    }

}
