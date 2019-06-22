package co.q64.dynamicalsystems.material.base;

import co.q64.dynamicalsystems.block.item.MaterialBlockItemFactory;

import javax.inject.Inject;
import java.util.Optional;

public abstract class ComponentBlock extends Component {
    protected @Inject MaterialBlockItemFactory blockFactory;

    protected ComponentBlock() {
        factory = Optional.of(material -> blockFactory.create(material, this));
    }
}
