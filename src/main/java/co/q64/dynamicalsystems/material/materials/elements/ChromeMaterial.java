package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ChromeMaterial extends MaterialMetal {

    @Inject
    protected ChromeMaterial() {
        name = "Chrome";
        element = Optional.of(Element.Cr);
        color = 0xFFAAAB;
    }

}
