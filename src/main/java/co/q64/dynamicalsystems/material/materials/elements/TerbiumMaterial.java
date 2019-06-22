package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TerbiumMaterial extends MaterialMetal {

    @Inject
    protected TerbiumMaterial() {
        name = "Terbium";
        element = Optional.of(Element.Tb);
        color = 0xFFFFFF;
    }

}
