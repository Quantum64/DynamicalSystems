package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class HolmiumMaterial extends MaterialMetal {

    @Inject
    protected HolmiumMaterial() {
        name = "Holmium";
        element = Optional.of(Element.Ho);
        color = 0xFFFFFF;
    }

}
