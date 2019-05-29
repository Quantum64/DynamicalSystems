package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CalciumMaterial extends MaterialMetal {

    @Inject
    protected CalciumMaterial() {
        name = "Calcium";
        element = Optional.of(Element.Ca);
        color = 0xDDDDAA;
    }

}
