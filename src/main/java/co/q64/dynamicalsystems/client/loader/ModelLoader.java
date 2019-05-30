package co.q64.dynamicalsystems.client.loader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestFactory;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

@Singleton
public class ModelLoader {
	protected @Inject @ModId String modId;
	protected @Inject ItemUtil itemUtil;
	protected @Inject MaterialTextureMap materialTextureMap;
	protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
	protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;

	protected @Inject ModelLoader() {}

	public void loadModels() {
		ModelLoadingRegistry.INSTANCE.registerResourceProvider(manager -> (resourceId, context) -> {
			if (resourceId.getNamespace().equals(modId)) {
				String[] parts = resourceId.getPath().split("/");
				String registry = parts[0];
				String resource = parts[1];
				for (int i = 2; i < parts.length; i++) {
					resource += "/" + parts[i];
				}
				Identifier identifier = new Identifier(modId, resource);
				if (itemUtil.getMaterialItem(identifier).isPresent()) {
					MaterialItem materialItem = itemUtil.getMaterialItem(identifier).get();
					if (registry.equals("item")) {
						if (materialItem.isBlock()) {
							return new JsonUnbakedModel(new ModelIdentifier(new Identifier(modId, resource), ""), Collections.emptyList(), Collections.emptyMap(), false, true, ModelTransformation.NONE, Collections.emptyList());
						} else {
							List<String> layers = materialTextureMap.getTextures(materialItem);
							Map<String, String> textures = new HashMap<>();
							for (int index = 0; index < layers.size(); index++) {
								textures.put("layer" + index, modId + ":" + layers.get(index));
							}
							return new JsonUnbakedModel(new Identifier("item/generated"), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
						}

					}
				}
			}
			return null;
		});

		ModelLoadingRegistry.INSTANCE.registerVariantProvider(manager -> (resourceId, context) -> {
			try {
				ModelIdentifier modelId = (ModelIdentifier) resourceId;
				if (modelId.getNamespace().equals(modId) && modelId.getVariant().equals("")) {
					Identifier identifier = new Identifier(modId, modelId.getPath());
					if (itemUtil.getMaterialItem(identifier).isPresent()) {
						MaterialItem materialItem = itemUtil.getMaterialItem(identifier).get();
						Map<String, String> textures = new HashMap<>();
						List<String> textureList = materialTextureMap.getTextures(materialItem);
						textures.put("all", modId + ":" + textureList.get(0));
						Component component = materialItem.getComponent();
						String modelOverload = component.getModel();
						if (component instanceof ComponentOre) {
							String generated = "generated_block_ore_" + ((ComponentOre) component).getBaseTexture().replace("/", "_");
							textures.put("base", modId + ":" + generated);
							alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(generated, ((ComponentOre) component).getBaseTexture(), textureList.get(0)));
						}
						if (textureList.size() > 1) {
							String generated = "generated_block_" + component.getTextureName();
							textures.put("all", modId + ":" + generated);
							textures.put("overlay", modId + ":" + textureList.get(1));
							alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(generated, textureList.get(0), textureList.get(1)));
							return new JsonUnbakedModel(new Identifier(modId, modelOverload.isEmpty() ? "block/block_material_overlay" : modelOverload), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
						}
						return new JsonUnbakedModel(new Identifier(modId, modelOverload.isEmpty() ? "block/block_material" : modelOverload), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
	}
}
