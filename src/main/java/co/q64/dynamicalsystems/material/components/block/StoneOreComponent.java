package co.q64.dynamicalsystems.material.components.block;

import co.q64.dynamicalsystems.material.base.ComponentOre;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Getter
@Singleton
public class StoneOreComponent extends ComponentOre {

    protected @Inject StoneOreComponent() {
        generate = material -> true;
        baseTexture = "block/stone";
    }
}
