package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class LongStickComponent extends Component {
	protected @Inject LongStickComponent() {
		prefix = "Long";
		name = "Stick";
		textureName = "stick_long";
		generate = material -> true;
	}
}
