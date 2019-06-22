package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class YttriumMaterial extends MaterialMetal {

    @Inject
    protected YttriumMaterial() {
        name = "Yttrium";
        element = Optional.of(Element.Y);
        color = 0xDCFADC;
    }

}
