package co.q64.dynamicalsystems.material.base;

import java.util.Optional;

import javax.inject.Inject;

import co.q64.dynamicalsystems.block.item.MaterialBlockItemFactory;

public abstract class ComponentBlock extends Component {
	protected @Inject MaterialBlockItemFactory blockFactory;
	
	protected ComponentBlock() {
		factory = Optional.of(material -> blockFactory.create(material, this));
	}
}
