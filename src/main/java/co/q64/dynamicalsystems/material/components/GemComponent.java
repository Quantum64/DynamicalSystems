package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GemComponent extends Component {
    protected @Inject GemComponent() {
        textureName = "gem";
        generate = material -> true;
    }
}
