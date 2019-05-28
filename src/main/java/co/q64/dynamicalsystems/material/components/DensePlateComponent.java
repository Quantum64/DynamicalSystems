package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class DensePlateComponent extends Component {
	protected @Inject DensePlateComponent() {
		prefix = "Dense";
		name = "Plate";
		textureName = "plate_dense";
		generate = material -> true;
	}
}
