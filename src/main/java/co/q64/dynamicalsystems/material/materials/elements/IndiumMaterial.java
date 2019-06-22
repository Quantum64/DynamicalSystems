package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class IndiumMaterial extends MaterialMetal {

    @Inject
    protected IndiumMaterial() {
        name = "Indium";
        element = Optional.of(Element.In);
        color = 0x6600BB;
    }

}
