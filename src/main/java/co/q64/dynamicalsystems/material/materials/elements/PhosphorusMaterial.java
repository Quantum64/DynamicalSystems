package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class PhosphorusMaterial extends MaterialMetal {

    @Inject
    protected PhosphorusMaterial() {
        name = "Phosphorus";
        element = Optional.of(Element.P);
        color = 0xC8C800;
    }

}
