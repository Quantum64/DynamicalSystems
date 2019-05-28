package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class StickComponent extends Component {
	protected @Inject StickComponent() {
		name = "Stick";
		textureName = "stick";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
