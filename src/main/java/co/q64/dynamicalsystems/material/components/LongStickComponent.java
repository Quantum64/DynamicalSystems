package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LongStickComponent extends Component {
    protected @Inject LongStickComponent() {
        prefix = "Long";
        name = "Stick";
        textureName = "stick_long";
        generate = material -> true;
    }
}
