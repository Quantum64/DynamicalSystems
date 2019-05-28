package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class CrushedComponent extends Component {
	protected @Inject CrushedComponent() {
		name = "Pieces";
		prefix = "Crushed";
		textureName = "crushed";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
