package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class TerbiumMaterial extends MaterialMetal {

    @Inject
    protected TerbiumMaterial() {
        name = "Terbium";
        element = Optional.of(Element.Tb);
        color = 0xFFFFFF;
    }

}
