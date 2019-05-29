package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class TungstenMaterial extends MaterialMetal {

    @Inject
    protected TungstenMaterial() {
        name = "Tungsten";
        element = Optional.of(Element.W);
        color = 0x323232;
    }

}
