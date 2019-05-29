package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CadmiumMaterial extends MaterialMetal {

    @Inject
    protected CadmiumMaterial() {
        name = "Cadmium";
        element = Optional.of(Element.Cd);
        color = 0x505060;
    }

}
