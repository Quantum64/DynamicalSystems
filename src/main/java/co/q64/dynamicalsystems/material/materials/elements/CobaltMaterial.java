package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CobaltMaterial extends MaterialMetal {

    @Inject
    protected CobaltMaterial() {
        name = "Cobalt";
        element = Optional.of(Element.Co);
        color = 0x2929BC;
    }

}
