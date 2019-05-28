package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class FineWireComponent extends Component {
	protected @Inject FineWireComponent() {
		prefix = "Fine";
		name = "Wire";
		textureName = "fine_wire";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
