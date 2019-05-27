package co.q64.dynamicalsystems.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.item.MaterialItemFactory;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.util.ItemRegistrationUtil;
import lombok.Getter;

@Singleton
public class MaterialItemLoader {
	protected @Inject Materials materials;
	protected @Inject Components components;
	protected @Inject MaterialRegistry materialRegistry;
	protected @Inject ComponentRegistry componentRegistry;
	protected @Inject MaterialItemFactory materialItemFactory;
	protected @Inject ItemRegistrationUtil itemRegistrationUtil;

	private @Getter List<MaterialItem> items = new ArrayList<>();
	private @Getter Map<Component, Map<Material, MaterialItem>> itemMap = new HashMap<>();

	protected @Inject MaterialItemLoader() {}

	public void registerItems() {
		for (Component component : componentRegistry.getComponents()) {
			for (Material material : materialRegistry.getMaterials()) {
				if (component.getGenerate().test(material)) {
					MaterialItem item = materialItemFactory.create(component, material);
					items.add(item);
					Map<Material, MaterialItem> materialMap = itemMap.get(component);
					if (materialMap == null) {
						materialMap = new HashMap<>();
						itemMap.put(component, materialMap);
					}
					materialMap.put(material, item);
					itemRegistrationUtil.registerItem(item);
				}
			}
		}
	}
}
