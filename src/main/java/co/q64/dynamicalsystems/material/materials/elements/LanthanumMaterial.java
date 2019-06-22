package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class LanthanumMaterial extends MaterialMetal {

    @Inject
    protected LanthanumMaterial() {
        name = "Lanthanum";
        element = Optional.of(Element.La);
        color = 0xFFFFFF;
    }

}
