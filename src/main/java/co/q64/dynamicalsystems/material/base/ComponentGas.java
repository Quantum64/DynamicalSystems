package co.q64.dynamicalsystems.material.base;

import co.q64.dynamicalsystems.block.item.MaterialBlockItemFactory;

import javax.inject.Inject;
import java.util.Optional;

public abstract class ComponentGas extends Component {
    protected @Inject MaterialBlockItemFactory blockFactory;

    protected ComponentGas() {
        factory = Optional.of(material -> blockFactory.create(material, this));
    }
}
