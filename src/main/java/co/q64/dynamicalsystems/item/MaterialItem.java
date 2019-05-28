package co.q64.dynamicalsystems.item;

import com.google.auto.factory.AutoFactory;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import lombok.Getter;

@Getter
@AutoFactory
public class MaterialItem extends BaseItem {
	private Material material;
	private Component component;

	public MaterialItem(Component component, Material material) {
		super(component.getPrefix() + (component.getPrefix().isEmpty() ? "" : " ") + material.getName() + (component.getName().isEmpty() ? "" : " ") + component.getName());
		this.material = material;
		this.component = component;
	}
}
