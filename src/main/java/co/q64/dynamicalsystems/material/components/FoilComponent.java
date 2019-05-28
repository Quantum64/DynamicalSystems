package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class FoilComponent extends Component {
	protected @Inject FoilComponent() {
		name = "Foil";
		textureName = "foil";
		generate = material -> true;
	}
}
