package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class TinyPlateComponent extends Component {
	protected @Inject TinyPlateComponent() {
		prefix = "Tiny";
		name = "Plate";
		textureName = "plate_tiny";
		generate = material -> true;
	}
}
