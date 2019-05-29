package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class EuropiumMaterial extends MaterialMetal {

    @Inject
    protected EuropiumMaterial() {
        name = "Europium";
        element = Optional.of(Element.Eu);
        color = 0xFFFFFF;
    }

}
