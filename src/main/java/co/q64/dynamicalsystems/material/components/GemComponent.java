package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.Component;

@Singleton
public class GemComponent extends Component {
	protected @Inject GemComponent() {
		textureName = "gem";
		generate = material -> true;
	}
}
