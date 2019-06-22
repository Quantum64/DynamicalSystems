package co.q64.dynamicalsystems.material.components.block;

import co.q64.dynamicalsystems.material.base.ComponentBlock;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BlockComponent extends ComponentBlock {
    protected @Inject BlockComponent() {
        generate = material -> true;
        name = "Block";
        textureName = "block";
    }
}
