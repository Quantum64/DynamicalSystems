package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class DoubleIngotComponent extends Component {
	protected @Inject DoubleIngotComponent() {
		prefix = "Double";
		name = "Ingot";
		textureName = "ingot_double";
		generate = material -> true;
	}
}
