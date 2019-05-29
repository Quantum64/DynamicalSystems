package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PlutoniumMaterial extends MaterialMetal {

    @Inject
    protected PlutoniumMaterial() {
        name = "Plutonium";
        element = Optional.of(Element.Pu);
        color = 0xF03232;
    }

}
