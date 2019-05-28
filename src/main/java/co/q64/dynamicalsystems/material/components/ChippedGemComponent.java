package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class ChippedGemComponent extends Component {
	protected @Inject ChippedGemComponent() {
		prefix = "Chipped";
		textureName = "gem_chipped";
		generate = material -> true;
	}
}
