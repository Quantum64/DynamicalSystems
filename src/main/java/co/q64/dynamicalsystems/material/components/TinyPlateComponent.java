package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TinyPlateComponent extends Component {
    protected @Inject TinyPlateComponent() {
        prefix = "Tiny";
        name = "Plate";
        textureName = "plate_tiny";
        generate = material -> true;
    }
}
