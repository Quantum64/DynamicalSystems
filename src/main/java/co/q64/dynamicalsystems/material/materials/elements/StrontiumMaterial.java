package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class StrontiumMaterial extends MaterialMetal {

    @Inject
    protected StrontiumMaterial() {
        name = "Strontium";
        element = Optional.of(Element.Sr);
        color = 0xC8C896;
    }

}
