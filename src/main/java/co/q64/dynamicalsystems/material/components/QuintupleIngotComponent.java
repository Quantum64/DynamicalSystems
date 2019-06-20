package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class QuintupleIngotComponent extends Component {
	protected @Inject QuintupleIngotComponent() {
		prefix = "Quintuple";
		name = "Ingot";
		textureName = "ingot_quintuple";
		generate = material -> true;
	}
}
