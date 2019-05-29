package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class BariumMaterial extends MaterialMetal {

    @Inject
    protected BariumMaterial() {
        name = "Barium";
        element = Optional.of(Element.Ba);
        color = 0xFFFFFF;
    }

}
