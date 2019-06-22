package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CobaltMaterial extends MaterialMetal {

    @Inject
    protected CobaltMaterial() {
        name = "Cobalt";
        element = Optional.of(Element.Co);
        color = 0x2929BC;
    }

}
