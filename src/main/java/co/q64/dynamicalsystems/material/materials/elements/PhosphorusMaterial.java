package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class PhosphorusMaterial extends MaterialMetal {

    @Inject
    protected PhosphorusMaterial() {
        name = "Phosphorus";
        element = Optional.of(Element.P);
        color = 0xC8C800;
    }

}
