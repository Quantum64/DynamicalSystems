package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class DysprosiumMaterial extends MaterialMetal {

    @Inject
    protected DysprosiumMaterial() {
        name = "Dysprosium";
        element = Optional.of(Element.Dy);
        color = 0xFFFFEE;
    }

}
