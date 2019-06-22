package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ChippedGemComponent extends Component {
    protected @Inject ChippedGemComponent() {
        prefix = "Chipped";
        textureName = "gem_chipped";
        generate = material -> true;
    }
}
