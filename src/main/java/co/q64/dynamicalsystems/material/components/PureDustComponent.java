package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class PureDustComponent extends Component {
	protected @Inject PureDustComponent() {
		prefix = "Purified";
		name = "Dust";
		textureName = "dust_pure";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
