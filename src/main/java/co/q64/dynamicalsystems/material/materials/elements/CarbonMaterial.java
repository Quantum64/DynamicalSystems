package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class CarbonMaterial extends MaterialMetal {

    @Inject
    protected CarbonMaterial() {
        name = "Carbon";
        element = Optional.of(Element.C);
        color = 0x333333;
    }

}
