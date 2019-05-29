package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PlatinumMaterial extends MaterialMetal {

    @Inject
    protected PlatinumMaterial() {
        name = "Platinum";
        element = Optional.of(Element.Pt);
        color = 0xFFFF99;
    }

}
