package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class IridiumMaterial extends MaterialMetal {

    @Inject
    protected IridiumMaterial() {
        name = "Iridium";
        element = Optional.of(Element.Ir);
        color = 0xFFFFFF;
    }

}
