package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class FluorineMaterial extends MaterialMetal {

    @Inject
    protected FluorineMaterial() {
        name = "Fluorine";
        element = Optional.of(Element.F);
        color = 0xFFFFAA;
    }

}
