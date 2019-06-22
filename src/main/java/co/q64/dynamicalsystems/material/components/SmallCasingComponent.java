package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SmallCasingComponent extends Component {
    protected @Inject SmallCasingComponent() {
        name = "Casing";
        prefix = "Small";
        textureName = "casing_small";
        generate = material -> true;
    }
}
