package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ZincMaterial extends MaterialMetal {

    @Inject
    protected ZincMaterial() {
        name = "Zinc";
        element = Optional.of(Element.Zn);
        color = 0xFAF0F0;
    }

}
