package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class MercuryMaterial extends MaterialMetal {

    @Inject
    protected MercuryMaterial() {
        name = "Mercury";
        element = Optional.of(Element.Hg);
        color = 0xFFDDDD;
    }

}
