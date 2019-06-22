package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DoubleIngotComponent extends Component {
    protected @Inject DoubleIngotComponent() {
        prefix = "Double";
        name = "Ingot";
        textureName = "ingot_double";
        generate = material -> true;
    }
}
