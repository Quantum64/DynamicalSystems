package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class BariumMaterial extends MaterialMetal {

    @Inject
    protected BariumMaterial() {
        name = "Barium";
        element = Optional.of(Element.Ba);
        color = 0xFFFFFF;
    }

}
