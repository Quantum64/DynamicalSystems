package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PotassiumMaterial extends MaterialMetal {

    @Inject
    protected PotassiumMaterial() {
        name = "Potassium";
        element = Optional.of(Element.K);
        color = 0xCECECE;
    }

}
