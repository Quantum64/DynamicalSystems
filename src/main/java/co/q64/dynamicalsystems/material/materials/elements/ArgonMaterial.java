package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ArgonMaterial extends MaterialMetal {

    @Inject
    protected ArgonMaterial() {
        name = "Argon";
        element = Optional.of(Element.Ar);
        color = 0xBBBB00;
    }

}
