package co.q64.dynamicalsystems.material.base;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.inject.Inject;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.ComponentRegistry;
import lombok.Getter;

@Getter
public abstract class Component {
	protected @Inject ComponentRegistry componentRegistry;

	protected Optional<Function<Material, MaterialItem>> factory = Optional.empty();
	protected Predicate<Material> generate = material -> false;
	protected String name = "";
	protected String prefix = "";
	protected String textureName = "";
	protected boolean hasTextureOverlay = false;

	protected Component() {}

	@Inject
	protected void register() {
		componentRegistry.register(this);
	}
}
