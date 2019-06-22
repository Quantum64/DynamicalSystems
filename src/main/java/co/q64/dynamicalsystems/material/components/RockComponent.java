package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RockComponent extends Component {
    protected @Inject RockComponent() {
        name = "Rock";
        textureName = "rock";
        generate = material -> true;
        hasTextureOverlay = true;
    }
}
