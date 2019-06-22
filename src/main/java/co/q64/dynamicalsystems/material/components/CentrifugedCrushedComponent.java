package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CentrifugedCrushedComponent extends Component {
    protected @Inject CentrifugedCrushedComponent() {
        name = "Pieces";
        prefix = "Centrifuged Crushed";
        textureName = "crushed_centrifuged";
        generate = material -> true;
    }
}
