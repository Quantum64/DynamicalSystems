package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PraseodymiumMaterial extends MaterialMetal {

    @Inject
    protected PraseodymiumMaterial() {
        name = "Praseodymium";
        element = Optional.of(Element.Pr);
        color = 0xCECECE;
    }

}
