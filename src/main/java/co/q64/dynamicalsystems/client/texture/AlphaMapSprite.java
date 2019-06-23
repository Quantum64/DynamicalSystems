package co.q64.dynamicalsystems.client.texture;

import com.google.auto.factory.AutoFactory;
import com.mojang.blaze3d.platform.TextureUtil;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.extensions.IForgeTextureAtlasSprite;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoFactory
public class AlphaMapSprite extends TextureAtlasSprite implements IForgeTextureAtlasSprite {
    private ResourceLocation base, overlay;

    protected AlphaMapSprite(ResourceLocation identifier, ResourceLocation base, ResourceLocation overlay) {
        super(identifier, 16, 16);
        this.base = base;
        this.overlay = overlay;
    }

    @Override
    public boolean load(IResourceManager manager, ResourceLocation location, Function<ResourceLocation, TextureAtlasSprite> textureGetter) {
        try {
            //IResource baseResource = manager.getResource(base);
            //PngFile basePng = new PngFile(baseResource.toString(), baseResource.getInputStream());
            //AnimationMetadataSection baseMeta = baseResource.getMetadata(AnimationMetadataSection.SERIALIZER);
            //TextureAtlasSprite baseGenerated = new FabricSprite(new ResourceLocation("dynamicalsystems", "generated_block_" + UUID.randomUUID()), basePng, baseMeta);
            //baseGenerated.load(manager.getResource(base), mipLevel);

            TextureAtlasSprite baseGenerated = textureGetter.apply(base);
            NativeImage baseImage = new NativeImage(baseGenerated.getWidth(), baseGenerated.getHeight(), true);
            for (int x = 0; x < baseImage.getWidth(); x++) {
                for (int y = 0; y < baseImage.getHeight(); y++) {
                    baseImage.setPixelRGBA(x, y, baseGenerated.getPixelRGBA(0, x, y));
                }
            }

            //Resource overlayResource = manager.getResource(overlay);
            //PngFile overlayPng = new PngFile(overlayResource.toString(), overlayResource.getInputStream());
            //AnimationResourceMetadata overlayMeta = overlayResource.getMetadata(AnimationResourceMetadata.READER);
            //TextureAtlasSprite overlayGenerated = new FabricSprite(new ResourceLocation("dynamicalsystems", "generated_block_" + UUID.randomUUID()), overlayPng, overlayMeta);
            //overlayGenerated.load(manager.getResource(overlay), mipLevel);
            //NativeImage overlayImage = ((SpriteMixin) overlayGenerated).getImages()[0];

            TextureAtlasSprite overlayGenerated = textureGetter.apply(overlay);
            for (int x = 0; x < baseImage.getWidth(); x++) {
                for (int y = 0; y < baseImage.getHeight(); y++) {
                    if ((overlayGenerated.getPixelRGBA(0, x, y) & 0xFF) != 0) {
                        baseImage.setPixelRGBA(x, y, 0x00000000);
                    }
                }
            }

            this.frames = new NativeImage[]{baseImage};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Set<ResourceLocation> getDependencies() {
        return Stream.of(base, overlay).collect(Collectors.toSet());
    }

    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        return true;
    }

    //TODO Extremely hacky bad thing
    // https://github.com/MinecraftForge/MinecraftForge/issues/5555

    protected class VeryDynamicTexture extends Texture implements AutoCloseable {
        private NativeImage dynamicTextureData;

        public void loadTexture(IResourceManager manager) throws IOException {
            dynamicTextureData = hackyLoad(manager);
            TextureUtil.prepareImage(this.getGlTextureId(), this.dynamicTextureData.getWidth(), this.dynamicTextureData.getHeight());
            updateDynamicTexture();
        }

        public void updateDynamicTexture() {
            this.bindTexture();
            this.dynamicTextureData.uploadTextureSub(0, 0, 0, false);
        }

        @Nullable
        public NativeImage getTextureData() {
            return this.dynamicTextureData;
        }

        public void setTextureData(NativeImage nativeImageIn) throws Exception {
            this.dynamicTextureData.close();
            this.dynamicTextureData = nativeImageIn;
        }

        public void close() {
            this.dynamicTextureData.close();
            this.deleteGlTexture();
            this.dynamicTextureData = null;
        }

        private NativeImage hackyLoad(IResourceManager manager) {
            try {
                IResource baseResource = manager.getResource(new ResourceLocation(base.getNamespace(), "textures/" + base.getPath() + ".png"));
                PngSizeInfo basePng = new PngSizeInfo(baseResource.toString(), baseResource.getInputStream());
                AnimationMetadataSection baseMeta = baseResource.getMetadata(AnimationMetadataSection.SERIALIZER);
                TextureAtlasSprite baseGenerated = new MyHackedSprite(new ResourceLocation("dynamicalsystems", "generated_block_" + UUID.randomUUID()), basePng, baseMeta);
                baseGenerated.loadSpriteFrames(baseResource, 1);
                NativeImage baseImage = new NativeImage(baseGenerated.getWidth(), baseGenerated.getHeight(), true);
                for (int x = 0; x < baseImage.getWidth(); x++) {
                    for (int y = 0; y < baseImage.getHeight(); y++) {
                        baseImage.setPixelRGBA(x, y, baseGenerated.getPixelRGBA(0, x, y));
                    }
                }

                IResource overlayResource = manager.getResource(new ResourceLocation(overlay.getNamespace(), "textures/" + overlay.getPath() + ".png"));
                PngSizeInfo overlayPng = new PngSizeInfo(overlayResource.toString(), overlayResource.getInputStream());
                AnimationMetadataSection overlayMeta = overlayResource.getMetadata(AnimationMetadataSection.SERIALIZER);
                TextureAtlasSprite overlayGenerated = new MyHackedSprite(new ResourceLocation("dynamicalsystems", "generated_block_" + UUID.randomUUID()), overlayPng, overlayMeta);
                baseGenerated.loadSpriteFrames(overlayResource, 1);
                for (int x = 0; x < baseImage.getWidth(); x++) {
                    for (int y = 0; y < baseImage.getHeight(); y++) {
                        if ((overlayGenerated.getPixelRGBA(0, x, y) & 0xFF) != 0) {
                            baseImage.setPixelRGBA(x, y, 0x00000000);
                        }
                    }
                }

                return baseImage;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return MissingTextureSprite.getDynamicTexture().getTextureData();
        }
    }

    private VeryDynamicTexture dynamicTexture;

    private static class MyHackedSprite extends TextureAtlasSprite {
        protected MyHackedSprite(ResourceLocation locationIn, PngSizeInfo sizeIn, @Nullable AnimationMetadataSection animationMetadataIn) {
            super(locationIn, sizeIn, animationMetadataIn);
        }
    }

    public VeryDynamicTexture getDynamic() {
        if (dynamicTexture == null) {
            dynamicTexture = new VeryDynamicTexture();
        }
        return dynamicTexture;
    }
}
