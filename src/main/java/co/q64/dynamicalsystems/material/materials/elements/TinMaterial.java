package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class TinMaterial extends MaterialMetal {

    @Inject
    protected TinMaterial() {
        name = "Tin";
        element = Optional.of(Element.Sn);
        color = 0xDCDCDC;
    }

}
