package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DustComponent extends Component {
    protected @Inject DustComponent() {
        name = "Dust";
        textureName = "dust";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
