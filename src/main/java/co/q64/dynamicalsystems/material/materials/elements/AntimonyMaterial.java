package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class AntimonyMaterial extends MaterialMetal {

    @Inject
    protected AntimonyMaterial() {
        name = "Antimony";
        element = Optional.of(Element.Sb);
        color = 0xDCDCC8;
    }

}
