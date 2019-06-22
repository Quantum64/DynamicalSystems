package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlateComponent extends Component {
    protected @Inject PlateComponent() {
        name = "Plate";
        textureName = "plate";
        generate = material -> true;
    }
}
