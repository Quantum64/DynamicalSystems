package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class LanthanumMaterial extends MaterialMetal {

    @Inject
    protected LanthanumMaterial() {
        name = "Lanthanum";
        element = Optional.of(Element.La);
        color = 0xFFFFFF;
    }

}
