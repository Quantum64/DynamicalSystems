package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FlawlessGemComponent extends Component {
    protected @Inject FlawlessGemComponent() {
        prefix = "Flawless";
        name = "Gem";
        textureName = "gem_flawless";
        generate = material -> true;
    }
}
