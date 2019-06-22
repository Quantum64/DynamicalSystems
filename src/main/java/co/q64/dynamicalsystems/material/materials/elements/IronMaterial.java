package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class IronMaterial extends MaterialMetal {

    @Inject
    protected IronMaterial() {
        name = "Iron";
        element = Optional.of(Element.Fe);
        color = 0xAAAAAA;
    }

}
