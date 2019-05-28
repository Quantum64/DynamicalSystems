package co.q64.dynamicalsystems.material.texture;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;

@Singleton
public class MaterialTextureMap {
	private static final String prefix = "items/material/";

	protected @Inject MaterialTextureMap() {}

	public List<String> getTextures(MaterialItem item) {
		List<String> result = new ArrayList<>();
		Material material = item.getMaterial();
		Component component = item.getComponent();
		String suffix = "default";
		if (material.getTextureOverrides().contains(component)) {
			suffix = material.getTextureOverrideFamily();
		}
		result.add(prefix + component.getTextureName() + "/" + suffix);
		if (component.isHasTextureOverlay()) {
			result.add(prefix + component.getTextureName() + "/" + suffix + "_overlay");
		}
		return result;
	}
}
