package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpringComponent extends Component {
    protected @Inject SpringComponent() {
        name = "Spring";
        textureName = "spring";
        generate = material -> true;
    }
}
