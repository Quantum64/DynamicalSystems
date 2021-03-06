package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CurvedPlateComponent extends Component {
    protected @Inject CurvedPlateComponent() {
        prefix = "Curved";
        name = "Plate";
        textureName = "plate_curved";
        generate = material -> true;
    }
}
