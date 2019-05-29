package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class VanadiumMaterial extends MaterialMetal {

    @Inject
    protected VanadiumMaterial() {
        name = "Vanadium";
        element = Optional.of(Element.V);
        color = 0x323232;
    }

}
