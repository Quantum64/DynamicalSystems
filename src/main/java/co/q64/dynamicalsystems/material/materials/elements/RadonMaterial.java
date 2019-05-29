package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class RadonMaterial extends MaterialMetal {

    @Inject
    protected RadonMaterial() {
        name = "Radon";
        element = Optional.of(Element.Rn);
        color = 0xFF00FF;
    }

}
