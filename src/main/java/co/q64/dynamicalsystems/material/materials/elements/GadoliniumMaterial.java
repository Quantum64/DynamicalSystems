package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class GadoliniumMaterial extends MaterialMetal {

    @Inject
    protected GadoliniumMaterial() {
        name = "Gadolinium";
        element = Optional.of(Element.Gd);
        color = 0xDDDDFF;
    }

}
