package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UraniumMaterial extends MaterialMetal {

    @Inject
    protected UraniumMaterial() {
        name = "Uranium";
        element = Optional.of(Element.U);
        color = 0x32F032;
    }

}
