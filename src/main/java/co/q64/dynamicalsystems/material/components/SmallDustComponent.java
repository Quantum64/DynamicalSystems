package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class SmallDustComponent extends Component {
	protected @Inject SmallDustComponent() {
		prefix = "Small";
		name = "Dust";
		textureName = "dust_small";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
