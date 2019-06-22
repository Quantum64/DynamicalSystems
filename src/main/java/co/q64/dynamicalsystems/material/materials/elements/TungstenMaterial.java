package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TungstenMaterial extends MaterialMetal {

    @Inject
    protected TungstenMaterial() {
        name = "Tungsten";
        element = Optional.of(Element.W);
        color = 0x323232;
    }

}
