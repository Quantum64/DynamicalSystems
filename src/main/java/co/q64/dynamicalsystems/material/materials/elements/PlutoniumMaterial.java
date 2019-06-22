package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class PlutoniumMaterial extends MaterialMetal {

    @Inject
    protected PlutoniumMaterial() {
        name = "Plutonium";
        element = Optional.of(Element.Pu);
        color = 0xF03232;
    }

}
