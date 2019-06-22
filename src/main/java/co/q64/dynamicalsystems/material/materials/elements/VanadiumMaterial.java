package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class VanadiumMaterial extends MaterialMetal {

    @Inject
    protected VanadiumMaterial() {
        name = "Vanadium";
        element = Optional.of(Element.V);
        color = 0x323232;
    }

}
