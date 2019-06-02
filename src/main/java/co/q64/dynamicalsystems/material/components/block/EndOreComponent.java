package co.q64.dynamicalsystems.material.components.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.ComponentOre;
import lombok.Getter;

@Getter
@Singleton
public class EndOreComponent extends ComponentOre {

	protected @Inject EndOreComponent() {
		generate = material -> true;
		baseTexture = "block/end_stone";
		prefix = "End";
	}
}
