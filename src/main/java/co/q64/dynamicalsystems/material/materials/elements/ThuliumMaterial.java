package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ThuliumMaterial extends MaterialMetal {

    @Inject
    protected ThuliumMaterial() {
        name = "Thulium";
        element = Optional.of(Element.Tm);
        color = 0xFFFFFF;
    }

}
