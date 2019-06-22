package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class HydrogenMaterial extends MaterialMetal {

    @Inject
    protected HydrogenMaterial() {
        name = "Hydrogen";
        element = Optional.of(Element.H);
        color = 0x00FFAA;
    }

}
