package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class IronMaterial extends MaterialMetal {

    @Inject
    protected IronMaterial() {
        name = "Iron";
        element = Optional.of(Element.Fe);
        color = 0xAAAAAA;
    }

}
