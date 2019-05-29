package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class NeodymiumMaterial extends MaterialMetal {

    @Inject
    protected NeodymiumMaterial() {
        name = "Neodymium";
        element = Optional.of(Element.Nd);
        color = 0x777777;
    }

}
