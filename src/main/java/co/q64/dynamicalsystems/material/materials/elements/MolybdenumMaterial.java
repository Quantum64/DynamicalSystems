package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class MolybdenumMaterial extends MaterialMetal {

    @Inject
    protected MolybdenumMaterial() {
        name = "Molybdenum";
        element = Optional.of(Element.Mo);
        color = 0xAAAADD;
    }

}
