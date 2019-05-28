package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class QuadrupleIngotComponent extends Component {
	protected @Inject QuadrupleIngotComponent() {
		prefix = "Quadruple";
		name = "Ingot";
		textureName = "ingot_quadruple";
		generate = material -> true;
	}
}
