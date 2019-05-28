package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class ScrapComponent extends Component {
	protected @Inject ScrapComponent() {
		name = "Scrap";
		textureName = "scrap";
		generate = material -> true;
	}
}
