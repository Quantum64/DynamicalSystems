package co.q64.dynamicalsystems.material.materials.elements;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class LeadMaterial extends MaterialMetal {

    @Inject
    protected LeadMaterial() {
        name = "Lead";
        element = Optional.of(Element.Pb);
        color = 0x8C648C;
    }

}
