package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FoilComponent extends Component {
    protected @Inject FoilComponent() {
        name = "Foil";
        textureName = "foil";
        generate = material -> true;
    }
}
