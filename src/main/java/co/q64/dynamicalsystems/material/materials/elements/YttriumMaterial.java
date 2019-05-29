package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class YttriumMaterial extends MaterialMetal {

    @Inject
    protected YttriumMaterial() {
        name = "Yttrium";
        element = Optional.of(Element.Y);
        color = 0xDCFADC;
    }

}
