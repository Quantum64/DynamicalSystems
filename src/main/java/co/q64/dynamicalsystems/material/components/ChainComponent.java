package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class ChainComponent extends Component {
	protected @Inject ChainComponent() {
		name = "Chain";
		textureName = "chain";
		generate = material -> true;
	}
}
