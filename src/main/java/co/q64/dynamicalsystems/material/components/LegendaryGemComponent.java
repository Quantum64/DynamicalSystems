package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class LegendaryGemComponent extends Component {
	protected @Inject LegendaryGemComponent() {
		prefix = "Legendary";
		name = "Gem";
		textureName = "gem_legendary";
		generate = material -> true;
	}
}
