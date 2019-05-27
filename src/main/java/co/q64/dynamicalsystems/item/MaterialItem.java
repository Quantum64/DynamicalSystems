package co.q64.dynamicalsystems.item;

import com.google.auto.factory.AutoFactory;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;

@AutoFactory
public class MaterialItem extends BaseItem {
	private Material material;
	private Component component;

	public MaterialItem(Component component, Material material) {
		super(material.getName() + " " + component.getName());
		this.material = material;
		this.component = component;
	}
}
