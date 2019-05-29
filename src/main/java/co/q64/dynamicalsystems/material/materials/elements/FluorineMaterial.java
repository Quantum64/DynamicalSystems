package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class FluorineMaterial extends MaterialMetal {

    @Inject
    protected FluorineMaterial() {
        name = "Fluorine";
        element = Optional.of(Element.F);
        color = 0xFFFFAA;
    }

}
