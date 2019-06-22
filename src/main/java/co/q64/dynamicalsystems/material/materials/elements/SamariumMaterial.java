package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class SamariumMaterial extends MaterialMetal {

    @Inject
    protected SamariumMaterial() {
        name = "Samarium";
        element = Optional.of(Element.Sm);
        color = 0xFFFFFF;
    }

}
