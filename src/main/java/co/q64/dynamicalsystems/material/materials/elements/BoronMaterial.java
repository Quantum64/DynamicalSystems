package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class BoronMaterial extends MaterialMetal {

    @Inject
    protected BoronMaterial() {
        name = "Boron";
        element = Optional.of(Element.B);
        color = 0xD2F0D2;
    }

}
