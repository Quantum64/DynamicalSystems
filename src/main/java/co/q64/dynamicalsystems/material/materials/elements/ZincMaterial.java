package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ZincMaterial extends MaterialMetal {

    @Inject
    protected ZincMaterial() {
        name = "Zinc";
        element = Optional.of(Element.Zn);
        color = 0xFAF0F0;
    }

}
