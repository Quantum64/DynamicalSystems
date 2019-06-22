package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SmallSpringComponent extends Component {
    protected @Inject SmallSpringComponent() {
        prefix = "Small";
        name = "Spring";
        textureName = "spring_small";
        generate = material -> true;
    }
}
