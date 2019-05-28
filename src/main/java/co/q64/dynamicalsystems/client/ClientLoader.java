package co.q64.dynamicalsystems.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.CommonLoader;
import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.util.Identifier;

@Singleton
public class ClientLoader {
	protected @Inject CommonLoader commonLoader;
	protected @Inject @ModId String modId;
	protected @Inject ItemUtil itemUtil;
	protected @Inject MaterialTextureMap materialTextureMap;

	protected @Inject ClientLoader() {}

	public void load() {
		commonLoader.load();

		ModelLoadingRegistry.INSTANCE.registerResourceProvider(manager -> (identifier, context) -> {
			if (identifier.getNamespace().equals(modId)) {
				Identifier itemIdentifier = new Identifier(modId, identifier.getPath().replace("item/", ""));
				if (itemUtil.getMaterialItem(itemIdentifier).isPresent()) {
					List<String> layers = materialTextureMap.getTextures(itemUtil.getMaterialItem(itemIdentifier).get());
					Map<String, String> textures = new HashMap<>();
					for (int index = 0; index < layers.size(); index++) {
						textures.put("layer" + index, modId + ":" + layers.get(index));
					}
					return new JsonUnbakedModel(new Identifier("item/generated"), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
				}
			}
			return null;
		});

		ColorProviderRegistry.ITEM.register((item, layer) -> {
			if (layer == 0) {
				return ((MaterialItem) item.getItem()).getMaterial().getColor();
			}
			return 0xFFFFFF;
		}, itemUtil.getMaterialItems().toArray(new MaterialItem[0]));
	}
}
