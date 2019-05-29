package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class IndiumMaterial extends MaterialMetal {

    @Inject
    protected IndiumMaterial() {
        name = "Indium";
        element = Optional.of(Element.In);
        color = 0x6600BB;
    }

}
