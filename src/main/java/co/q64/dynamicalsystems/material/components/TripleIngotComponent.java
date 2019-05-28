package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class TripleIngotComponent extends Component {
	protected @Inject TripleIngotComponent() {
		prefix = "Triple";
		name = "Ingot";
		textureName = "ingot_triple";
		generate = material -> true;
	}
}
