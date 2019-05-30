package co.q64.dynamicalsystems.client.loader;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.block.MaterialBlock;
import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequest;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestFactory;
import co.q64.dynamicalsystems.client.texture.AlphaMapSpriteFactory;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.item.SimpleMaterialItem;
import co.q64.dynamicalsystems.loader.CommonLoader;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.util.ItemUtil;
import co.q64.dynamicalsystems.util.Logger;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.Block;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

@Singleton
public class ClientLoader {
	protected @Inject CommonLoader commonLoader;
	protected @Inject @ModId String modId;
	protected @Inject ItemUtil itemUtil;
	protected @Inject MaterialTextureMap materialTextureMap;
	protected @Inject AlphaMapSpriteFactory alphaMapSpriteFactory;
	protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;
	protected @Inject Logger logger;

	private Set<AlphaMapRequest> alphaMapRequests = new HashSet<>();

	protected @Inject ClientLoader() {}

	public void load() {
		commonLoader.load();

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
					if (registry.equals("block")) {

					}
					if (registry.equals("blockstate")) {

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
							alphaMapRequests.add(alphaMapRequestFactory.create(generated, ((ComponentOre) component).getBaseTexture(), textureList.get(0)));
						}
						if (textureList.size() > 1) {
							String generated = "generated_block_" + component.getTextureName();
							textures.put("all", modId + ":" + generated);
							textures.put("overlay", modId + ":" + textureList.get(1));
							alphaMapRequests.add(alphaMapRequestFactory.create(generated, textureList.get(0), textureList.get(1)));
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

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((texture, registry) -> {
			logger.info("Processing " + alphaMapRequests.size() + " alphamap requests...");
			for (AlphaMapRequest request : alphaMapRequests) {
				registry.register(alphaMapSpriteFactory.create(new Identifier(modId, request.getGeneratedTexture()), new Identifier("textures/" + request.getBaseTexture() + ".png"), new Identifier(modId, "textures/" + request.getOverlayTexture() + ".png")));
			}
		});

		ColorProviderRegistry.ITEM.register((item, layer) -> {
			if (layer == 0) {
				return ((SimpleMaterialItem) item.getItem()).getMaterial().getColor();
			}
			return 0xFFFFFF;
		}, itemUtil.getMaterialItems().stream().filter(MaterialItem::isItem).toArray(SimpleMaterialItem[]::new));

		ColorProviderRegistry.ITEM.register((item, layer) -> {
			if (layer == 0) {
				return ((MaterialBlockItem) item.getItem()).getMaterial().getColor();
			}
			return 0xFFFFFF;
		}, itemUtil.getMaterialItems().stream().filter(MaterialItem::isBlock).toArray(MaterialBlockItem[]::new));

		ColorProviderRegistry.BLOCK.register((state, view, position, layer) -> {
			//if (layer == 0) {
			return ((MaterialBlock) state.getBlock()).getItem().getMaterial().getColor();
			//}
			//return 0xFFFFFF;
		}, itemUtil.getMaterialItems().stream().filter(MaterialItem::isBlock).map(MaterialItem::getBlock).toArray(Block[]::new));
	}
}
