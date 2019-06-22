package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RingComponent extends Component {
    protected @Inject RingComponent() {
        name = "Ring";
        textureName = "ring";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
