package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class NickelMaterial extends MaterialMetal {

    @Inject
    protected NickelMaterial() {
        name = "Nickel";
        element = Optional.of(Element.Ni);
        color = 0xAAAAFF;
    }

}
