package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class SmallGearComponent extends Component {
	protected @Inject SmallGearComponent() {
		prefix = "Small";
		name = "Gear";
		textureName = "gear";
		generate = material -> true;
	}
}
