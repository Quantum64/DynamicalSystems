package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class LeadMaterial extends MaterialMetal {

    @Inject
    protected LeadMaterial() {
        name = "Lead";
        element = Optional.of(Element.Pb);
        color = 0x8C648C;
    }

}
