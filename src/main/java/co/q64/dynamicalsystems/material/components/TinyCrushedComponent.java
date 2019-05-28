package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class TinyCrushedComponent extends Component {
	protected @Inject TinyCrushedComponent() {
		name = "Pieces";
		prefix = "Tiny Crushed";
		textureName = "crushed_tiny";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
