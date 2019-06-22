package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class SiliconMaterial extends MaterialMetal {

    @Inject
    protected SiliconMaterial() {
        name = "Silicon";
        element = Optional.of(Element.Si);
        color = 0x3C3C50;
    }

}
