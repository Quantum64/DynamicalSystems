package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TelluriumMaterial extends MaterialMetal {

    @Inject
    protected TelluriumMaterial() {
        name = "Tellurium";
        element = Optional.of(Element.Te);
        color = 0xFFFFFF;
    }

}
