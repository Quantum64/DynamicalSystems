package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class PlateComponent extends Component {
	protected @Inject PlateComponent() {
		name = "Plate";
		textureName = "plate";
		generate = material -> true;
	}
}
