package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PurifiedCrushedComponent extends Component {
    protected @Inject PurifiedCrushedComponent() {
        name = "Pieces";
        prefix = "Purified Crushed";
        textureName = "crushed_purified";
        generate = material -> true;
    }
}
