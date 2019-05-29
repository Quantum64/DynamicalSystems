package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class BerylliumMaterial extends MaterialMetal {

    @Inject
    protected BerylliumMaterial() {
        name = "Beryllium";
        element = Optional.of(Element.Be);
        color = 0x64B464;
    }

}
