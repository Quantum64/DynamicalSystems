package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LensComponent extends Component {
    protected @Inject LensComponent() {
        name = "Lens";
        textureName = "lens";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
