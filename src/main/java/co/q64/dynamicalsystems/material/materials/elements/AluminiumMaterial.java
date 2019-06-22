package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class AluminiumMaterial extends MaterialMetal {

    @Inject
    protected AluminiumMaterial() {
        name = "Aluminium";
        element = Optional.of(Element.Al);
        color = 0x80C8F0;
    }

}
