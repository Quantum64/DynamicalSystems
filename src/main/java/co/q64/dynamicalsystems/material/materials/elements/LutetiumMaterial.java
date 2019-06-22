package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class LutetiumMaterial extends MaterialMetal {

    @Inject
    protected LutetiumMaterial() {
        name = "Lutetium";
        element = Optional.of(Element.Lu);
        color = 0xFFFFFF;
    }

}
