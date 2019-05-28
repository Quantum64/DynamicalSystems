package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class NuggetComponent extends Component {
	protected @Inject NuggetComponent() {
		name = "Nugget";
		textureName = "nugget";
		generate = material -> true;
		hasTextureOverlay = true;
	}
}
