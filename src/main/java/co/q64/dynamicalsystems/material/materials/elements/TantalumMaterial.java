package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TantalumMaterial extends MaterialMetal {

    @Inject
    protected TantalumMaterial() {
        name = "Tantalum";
        element = Optional.of(Element.Ta);
        color = 0xFFFFFF;
    }

}
