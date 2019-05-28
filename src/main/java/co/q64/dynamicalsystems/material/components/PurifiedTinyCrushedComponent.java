package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class PurifiedTinyCrushedComponent extends Component {
	protected @Inject PurifiedTinyCrushedComponent() {
		name = "Pieces";
		prefix = "Purified Tiny Crushed";
		textureName = "crushed_tiny_purified";
		generate = material -> true;
	}
}
