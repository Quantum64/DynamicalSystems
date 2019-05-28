package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class GearComponent extends Component {
	protected @Inject GearComponent() {
		name = "Gear";
		textureName = "gear";
		generate = material -> true;
	}
}
