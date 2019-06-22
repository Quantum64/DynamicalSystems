package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class PlatinumMaterial extends MaterialMetal {

    @Inject
    protected PlatinumMaterial() {
        name = "Platinum";
        element = Optional.of(Element.Pt);
        color = 0xFFFF99;
    }

}
