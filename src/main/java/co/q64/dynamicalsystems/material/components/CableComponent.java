package co.q64.dynamicalsystems.material.components;

import java.util.Optional;

import javax.inject.Inject;

import co.q64.dynamicalsystems.block.item.CableBlockItemFactory;
import co.q64.dynamicalsystems.material.base.ComponentBlock;
import lombok.Getter;

@Getter
public class CableComponent extends ComponentBlock {
	protected @Inject CableComponent(CableBlockItemFactory blockFactory) {
		factory = Optional.of(material -> blockFactory.create(material, this));
		generate = material -> true;
		name = "Cable";
		textureName = "cable";
		model = "block/block_cable";
	}
}
