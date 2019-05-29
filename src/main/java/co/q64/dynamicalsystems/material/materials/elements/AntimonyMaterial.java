package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class AntimonyMaterial extends MaterialMetal {

    @Inject
    protected AntimonyMaterial() {
        name = "Antimony";
        element = Optional.of(Element.Sb);
        color = 0xDCDCC8;
    }

}
