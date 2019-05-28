package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class IngotComponent extends Component {
	protected @Inject IngotComponent() {
		name = "Ingot";
		textureName = "ingot";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
