package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class MagnesiumMaterial extends MaterialMetal {

    @Inject
    protected MagnesiumMaterial() {
        name = "Magnesium";
        element = Optional.of(Element.Mg);
        color = 0xFFBBBB;
    }

}
