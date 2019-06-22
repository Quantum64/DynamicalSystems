package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ArgonMaterial extends MaterialMetal {

    @Inject
    protected ArgonMaterial() {
        name = "Argon";
        element = Optional.of(Element.Ar);
        color = 0xBBBB00;
    }

}
