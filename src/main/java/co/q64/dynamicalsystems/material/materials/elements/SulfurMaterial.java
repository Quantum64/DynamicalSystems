package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class SulfurMaterial extends MaterialMetal {

    @Inject
    protected SulfurMaterial() {
        name = "Sulfur";
        element = Optional.of(Element.S);
        color = 0xC8C800;
    }

}
