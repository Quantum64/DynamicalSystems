package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ArsenicMaterial extends MaterialMetal {

    @Inject
    protected ArsenicMaterial() {
        name = "Arsenic";
        element = Optional.of(Element.As);
        color = 0xDDDDDD;
    }

}
