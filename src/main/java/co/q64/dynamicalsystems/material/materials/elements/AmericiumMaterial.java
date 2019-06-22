package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class AmericiumMaterial extends MaterialMetal {

    @Inject
    protected AmericiumMaterial() {
        name = "Americium";
        element = Optional.of(Element.Am);
        color = 0xC8C8C8;
    }

}
 