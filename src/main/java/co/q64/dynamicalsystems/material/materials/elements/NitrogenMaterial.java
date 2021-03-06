package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class NitrogenMaterial extends MaterialMetal {

    @Inject
    protected NitrogenMaterial() {
        name = "Nitrogen";
        element = Optional.of(Element.N);
        color = 0x7090AF;
    }

}
