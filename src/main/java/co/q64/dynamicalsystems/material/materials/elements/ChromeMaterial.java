package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class ChromeMaterial extends MaterialMetal {

    @Inject
    protected ChromeMaterial() {
        name = "Chrome";
        element = Optional.of(Element.Cr);
        color = 0xFFAAAB;
    }

}
