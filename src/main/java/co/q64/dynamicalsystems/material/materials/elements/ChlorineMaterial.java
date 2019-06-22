package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ChlorineMaterial extends MaterialMetal {

    @Inject
    protected ChlorineMaterial() {
        name = "Chlorine";
        element = Optional.of(Element.Cl);
        color = 0xEEEECC;
    }

}
