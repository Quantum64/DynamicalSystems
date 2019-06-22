package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CalciumMaterial extends MaterialMetal {

    @Inject
    protected CalciumMaterial() {
        name = "Calcium";
        element = Optional.of(Element.Ca);
        color = 0xDDDDAA;
    }

}
