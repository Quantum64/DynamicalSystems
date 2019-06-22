package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class NiobiumMaterial extends MaterialMetal {

    @Inject
    protected NiobiumMaterial() {
        name = "Niobium";
        element = Optional.of(Element.Nb);
        color = 0x9486AA;
    }

}
