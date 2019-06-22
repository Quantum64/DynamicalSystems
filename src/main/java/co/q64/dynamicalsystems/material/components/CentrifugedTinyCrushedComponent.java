package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CentrifugedTinyCrushedComponent extends Component {
    protected @Inject CentrifugedTinyCrushedComponent() {
        name = "Pieces";
        prefix = "Centrifuged Tiny Crushed";
        textureName = "crushed_tiny_centrifuged";
        generate = material -> true;
    }
}
