package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FineWireComponent extends Component {
    protected @Inject FineWireComponent() {
        prefix = "Fine";
        name = "Wire";
        textureName = "fine_wire";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
