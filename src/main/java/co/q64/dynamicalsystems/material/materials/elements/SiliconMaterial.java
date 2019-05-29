package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class SiliconMaterial extends MaterialMetal {

    @Inject
    protected SiliconMaterial() {
        name = "Silicon";
        element = Optional.of(Element.Si);
        color = 0x3C3C50;
    }

}
