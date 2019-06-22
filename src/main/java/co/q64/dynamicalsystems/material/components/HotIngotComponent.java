package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HotIngotComponent extends Component {
    protected @Inject HotIngotComponent() {
        prefix = "Hot";
        name = "Ingot";
        textureName = "ingot_hot";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
