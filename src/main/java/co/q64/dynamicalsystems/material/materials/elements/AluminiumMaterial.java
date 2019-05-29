package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class AluminiumMaterial extends MaterialMetal {

    @Inject
    protected AluminiumMaterial() {
        name = "Aluminium";
        element = Optional.of(Element.Al);
        color = 0x80C8F0;
    }

}
