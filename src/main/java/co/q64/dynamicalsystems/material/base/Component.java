package co.q64.dynamicalsystems.material.base;

import java.util.function.Predicate;

import javax.inject.Inject;

import co.q64.dynamicalsystems.material.ComponentRegistry;
import lombok.Getter;

@Getter
public abstract class Component {
	protected @Inject ComponentRegistry componentRegistry;

	protected Predicate<Material> generate = material -> false;
	protected String name;

	protected Component() {}

	@Inject
	protected void register() {
		componentRegistry.register(this);
	}
}
