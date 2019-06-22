package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class BismuthMaterial extends MaterialMetal {

    @Inject
    protected BismuthMaterial() {
        name = "Bismuth";
        element = Optional.of(Element.Bi);
        color = 0x64A0A0;
    }

}
