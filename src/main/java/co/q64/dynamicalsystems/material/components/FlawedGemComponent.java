package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FlawedGemComponent extends Component {
    protected @Inject FlawedGemComponent() {
        prefix = "Flawed";
        name = "Gem";
        textureName = "gem_flawed";
        generate = material -> true;
    }
}
