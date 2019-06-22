package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class GoldMaterial extends MaterialMetal {

    @Inject
    protected GoldMaterial() {
        name = "Gold";
        element = Optional.of(Element.Au);
        color = 0xFFFF00;
    }

}
