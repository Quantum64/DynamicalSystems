package co.q64.dynamicalsystems.material.components.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.ComponentBlock;

@Singleton
public class BlockComponent extends ComponentBlock {
	protected @Inject BlockComponent() {
		generate = material -> true;
		name = "Block";
		textureName = "block";
	}
}
