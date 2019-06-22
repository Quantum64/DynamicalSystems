package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImpureDustComponent extends Component {
    protected @Inject ImpureDustComponent() {
        prefix = "Impure";
        name = "Dust";
        textureName = "dust_impure";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
