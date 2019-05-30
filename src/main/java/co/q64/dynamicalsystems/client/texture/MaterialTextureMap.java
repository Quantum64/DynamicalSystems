package co.q64.dynamicalsystems.client.texture;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;

@Singleton
public class MaterialTextureMap {

	protected @Inject MaterialTextureMap() {}

	public List<String> getTextures(MaterialItem item) {
		List<String> result = new ArrayList<>();
		Material material = item.getMaterial();
		Component component = item.getComponent();
		String suffix = "default";
		if (material.getTextureOverrides().contains(component)) {
			suffix = material.getTextureOverrideFamily();
		}
		String prefix = item.isBlock() ? "block" : "item";
		result.add(prefix + "/material/" + component.getTextureName() + "/" + suffix);
		if (component.isHasTextureOverlay()) {
			result.add(prefix + "/material/" + component.getTextureName() + "/" + suffix + "_overlay");
		}
		return result;
	}
}
