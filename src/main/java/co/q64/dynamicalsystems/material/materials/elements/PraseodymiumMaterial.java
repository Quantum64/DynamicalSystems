package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class PraseodymiumMaterial extends MaterialMetal {

    @Inject
    protected PraseodymiumMaterial() {
        name = "Praseodymium";
        element = Optional.of(Element.Pr);
        color = 0xCECECE;
    }

}
