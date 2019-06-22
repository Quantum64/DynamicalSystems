package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class NeodymiumMaterial extends MaterialMetal {

    @Inject
    protected NeodymiumMaterial() {
        name = "Neodymium";
        element = Optional.of(Element.Nd);
        color = 0x777777;
    }

}
