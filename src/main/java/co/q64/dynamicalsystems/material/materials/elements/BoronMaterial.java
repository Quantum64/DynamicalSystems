package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class BoronMaterial extends MaterialMetal {

    @Inject
    protected BoronMaterial() {
        name = "Boron";
        element = Optional.of(Element.B);
        color = 0xD2F0D2;
    }

}
