package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ChlorineMaterial extends MaterialMetal {

    @Inject
    protected ChlorineMaterial() {
        name = "Chlorine";
        element = Optional.of(Element.Cl);
        color = 0xEEEECC;
    }

}
