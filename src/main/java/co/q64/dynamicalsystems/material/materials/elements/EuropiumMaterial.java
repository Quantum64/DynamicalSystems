package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class EuropiumMaterial extends MaterialMetal {

    @Inject
    protected EuropiumMaterial() {
        name = "Europium";
        element = Optional.of(Element.Eu);
        color = 0xFFFFFF;
    }

}
