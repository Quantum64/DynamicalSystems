package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class RingComponent extends Component {
	protected @Inject RingComponent() {
		name = "Ring";
		textureName = "ring";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
