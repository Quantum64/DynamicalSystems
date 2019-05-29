package co.q64.dynamicalsystems.material.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.base.ComponentOre;
import lombok.Getter;

@Getter
@Singleton
public class NetherOreComponent extends ComponentOre {

	protected @Inject NetherOreComponent() {
		generate = material -> true;
		baseTexture = "block/netherrack";
		prefix = "Nether";
	}
}
