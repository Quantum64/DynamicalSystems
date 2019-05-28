package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class DustComponent extends Component {
	protected @Inject DustComponent() {
		name = "Dust";
		textureName = "dust";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
