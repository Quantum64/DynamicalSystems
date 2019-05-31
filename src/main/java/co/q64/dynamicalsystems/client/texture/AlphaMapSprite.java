package co.q64.dynamicalsystems.client.texture;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.google.auto.factory.AutoFactory;

import co.q64.dynamicalsystems.mixin.client.SpriteMixin;
import net.fabricmc.fabric.api.client.texture.CustomSpriteLoader;
import net.fabricmc.fabric.api.client.texture.DependentSprite;
import net.fabricmc.fabric.impl.client.texture.FabricSprite;
import net.minecraft.client.resource.metadata.AnimationResourceMetadata;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.PngFile;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

@AutoFactory
public class AlphaMapSprite extends Sprite implements CustomSpriteLoader, DependentSprite {
	private Identifier base, overlay;

	protected AlphaMapSprite(Identifier identifier, Identifier base, Identifier overlay) {
		super(identifier, 16, 16);
		this.base = base;
		this.overlay = overlay;
	}

	@Override
	public boolean load(ResourceManager manager, int mipLevel) throws IOException {
		try {
			Resource baseResource = manager.getResource(base);
			PngFile basePng = new PngFile(baseResource.toString(), baseResource.getInputStream());
			AnimationResourceMetadata baseMeta = baseResource.getMetadata(AnimationResourceMetadata.READER);
			Sprite baseGenerated = new FabricSprite(new Identifier("dynamicalsystems", "generated_block_" + UUID.randomUUID()), basePng, baseMeta);
			baseGenerated.load(manager.getResource(base), mipLevel);
			NativeImage baseImage = ((SpriteMixin) baseGenerated).getImages()[0];

			Resource overlayResource = manager.getResource(overlay);
			PngFile overlayPng = new PngFile(overlayResource.toString(), overlayResource.getInputStream());
			AnimationResourceMetadata overlayMeta = overlayResource.getMetadata(AnimationResourceMetadata.READER);
			Sprite overlayGenerated = new FabricSprite(new Identifier("dynamicalsystems", "generated_block_" + UUID.randomUUID()), overlayPng, overlayMeta);
			overlayGenerated.load(manager.getResource(overlay), mipLevel);
			NativeImage overlayImage = ((SpriteMixin) overlayGenerated).getImages()[0];

			for (int x = 0; x < baseImage.getWidth(); x++) {
				for (int y = 0; y < baseImage.getHeight(); y++) {
					if ((overlayImage.getPixelRGBA(x, y) & 0xFF) != 0) {
						baseImage.setPixelRGBA(x, y, 0x00000000);
					}
				}
			}

			this.images = new NativeImage[] { baseImage };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Set<Identifier> getDependencies() {
		return new HashSet<>();
	}
}
