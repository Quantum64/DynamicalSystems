package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class AmericiumMaterial extends MaterialMetal {

    @Inject
    protected AmericiumMaterial() {
        name = "Americium";
        element = Optional.of(Element.Am);
        color = 0xC8C8C8;
    }

}
