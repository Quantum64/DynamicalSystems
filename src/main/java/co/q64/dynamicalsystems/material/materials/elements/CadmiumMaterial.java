package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CadmiumMaterial extends MaterialMetal {

    @Inject
    protected CadmiumMaterial() {
        name = "Cadmium";
        element = Optional.of(Element.Cd);
        color = 0x505060;
    }

}
