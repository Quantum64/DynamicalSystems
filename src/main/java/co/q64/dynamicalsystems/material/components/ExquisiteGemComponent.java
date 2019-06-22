package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExquisiteGemComponent extends Component {
    protected @Inject ExquisiteGemComponent() {
        prefix = "Exquisite";
        name = "Gem";
        textureName = "gem_exquisite";
        generate = material -> true;
    }
}
