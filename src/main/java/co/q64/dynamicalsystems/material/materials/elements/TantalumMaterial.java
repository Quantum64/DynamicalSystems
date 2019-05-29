package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class TantalumMaterial extends MaterialMetal {

    @Inject
    protected TantalumMaterial() {
        name = "Tantalum";
        element = Optional.of(Element.Ta);
        color = 0xFFFFFF;
    }

}
