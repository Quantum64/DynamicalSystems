package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ThoriumMaterial extends MaterialMetal {

    @Inject
    protected ThoriumMaterial() {
        name = "Thorium";
        element = Optional.of(Element.Th);
        color = 0x001E00;
    }

}
