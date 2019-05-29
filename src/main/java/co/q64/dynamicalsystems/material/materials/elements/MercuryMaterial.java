package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class MercuryMaterial extends MaterialMetal {

    @Inject
    protected MercuryMaterial() {
        name = "Mercury";
        element = Optional.of(Element.Hg);
        color = 0xFFDDDD;
    }

}
