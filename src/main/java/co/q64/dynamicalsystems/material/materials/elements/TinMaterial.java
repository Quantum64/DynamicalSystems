package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TinMaterial extends MaterialMetal {

    @Inject
    protected TinMaterial() {
        name = "Tin";
        element = Optional.of(Element.Sn);
        color = 0xDCDCDC;
    }

}
