package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class BismuthMaterial extends MaterialMetal {

    @Inject
    protected BismuthMaterial() {
        name = "Bismuth";
        element = Optional.of(Element.Bi);
        color = 0x64A0A0;
    }

}
