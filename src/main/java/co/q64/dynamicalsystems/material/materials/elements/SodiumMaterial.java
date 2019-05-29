package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class SodiumMaterial extends MaterialMetal {

    @Inject
    protected SodiumMaterial() {
        name = "Sodium";
        element = Optional.of(Element.Na);
        color = 0x000096;
    }

}
