package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class BerylliumMaterial extends MaterialMetal {

    @Inject
    protected BerylliumMaterial() {
        name = "Beryllium";
        element = Optional.of(Element.Be);
        color = 0x64B464;
    }

}
