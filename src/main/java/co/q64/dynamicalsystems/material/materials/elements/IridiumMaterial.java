package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class IridiumMaterial extends MaterialMetal {

    @Inject
    protected IridiumMaterial() {
        name = "Iridium";
        element = Optional.of(Element.Ir);
        color = 0xFFFFFF;
    }

}
