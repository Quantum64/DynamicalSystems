package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class NickelMaterial extends MaterialMetal {

    @Inject
    protected NickelMaterial() {
        name = "Nickel";
        element = Optional.of(Element.Ni);
        color = 0xAAAAFF;
    }

}
