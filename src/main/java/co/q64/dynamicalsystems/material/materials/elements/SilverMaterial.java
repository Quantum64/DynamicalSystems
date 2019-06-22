package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class SilverMaterial extends MaterialMetal {

    @Inject
    protected SilverMaterial() {
        name = "Silver";
        element = Optional.of(Element.Ag);
        color = 0xDCDCFF;
    }

}
