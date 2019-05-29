package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class TelluriumMaterial extends MaterialMetal {

    @Inject
    protected TelluriumMaterial() {
        name = "Tellurium";
        element = Optional.of(Element.Te);
        color = 0xFFFFFF;
    }

}
