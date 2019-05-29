package co.q64.dynamicalsystems.material.materials.elements;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.MaterialMetal;
import co.q64.dynamicalsystems.type.Element;

@Singleton
public class OsmiumMaterial extends MaterialMetal {

    @Inject
    protected OsmiumMaterial() {
        name = "Osmium";
        element = Optional.of(Element.Os);
        color = 0x5050FF;
    }

}
