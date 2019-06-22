package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class OxygenMaterial extends MaterialMetal {

    @Inject
    protected OxygenMaterial() {
        name = "Oxygen";
        element = Optional.of(Element.O);
        color = 0x90AAEE;
    }

}
