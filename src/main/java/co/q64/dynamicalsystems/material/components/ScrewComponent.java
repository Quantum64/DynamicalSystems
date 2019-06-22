package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScrewComponent extends Component {
    protected @Inject ScrewComponent() {
        name = "Screw";
        textureName = "screw";
        generate = material -> true;
    }
}
