package co.q64.dynamicalsystems.material;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;

@FunctionalInterface
public interface ComponentHandler<T extends Component> {
	public void handle(T component, Material material);

	@SuppressWarnings("unchecked")
	public default void handleUnchecked(Component component, Material material) {
		handle((T) component, material);
	}
}
