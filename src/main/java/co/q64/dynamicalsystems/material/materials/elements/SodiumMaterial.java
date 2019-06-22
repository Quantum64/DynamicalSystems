package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class SodiumMaterial extends MaterialMetal {

    @Inject
    protected SodiumMaterial() {
        name = "Sodium";
        element = Optional.of(Element.Na);
        color = 0x000096;
    }

}
