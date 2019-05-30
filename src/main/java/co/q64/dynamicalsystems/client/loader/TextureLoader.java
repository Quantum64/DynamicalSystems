package co.q64.dynamicalsystems.client.loader;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.block.MaterialBlock;
import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequest;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.AlphaMapSpriteFactory;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.item.SimpleMaterialItem;
import co.q64.dynamicalsystems.util.ItemUtil;
import co.q64.dynamicalsystems.util.Logger;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.Block;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

@Singleton
public class TextureLoader {
	protected @Inject Logger logger;
	protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
	protected @Inject AlphaMapSpriteFactory alphaMapSpriteFactory;
	protected @Inject @ModId String modId;
	protected @Inject ItemUtil itemUtil;

	protected @Inject TextureLoader() {}

	public void loadTextures() {
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((texture, registry) -> {
			logger.info("Processing " + alphaMapRequestRegistry.getRequests().size() + " alphamap requests...");
			for (AlphaMapRequest request : alphaMapRequestRegistry.getRequests()) {
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
			return ((MaterialBlock) state.getBlock()).getItem().getMaterial().getColor();
		}, itemUtil.getMaterialItems().stream().filter(MaterialItem::isBlock).map(MaterialItem::getBlock).toArray(Block[]::new));
	}
}
