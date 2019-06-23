package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequest;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.AlphaMapSpriteFactory;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TextureLoader {
    protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    protected @Inject AlphaMapSpriteFactory alphaMapSpriteFactory;
    protected @Inject Logger logger;
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifierUtil;

    protected @Inject TextureLoader() {}

    public void loadTextures(AtlasTexture map) {
        logger.info("Processing " + alphaMapRequestRegistry.getRequests().size() + " alphamap requests...");
        for (AlphaMapRequest request : alphaMapRequestRegistry.getRequests()) {
            //TODO Bad hack https://github.com/MinecraftForge/MinecraftForge/issues/5555
            Minecraft.getInstance().getTextureManager().loadTexture(request.getGeneratedTexture(), alphaMapSpriteFactory.create(request.getGeneratedTexture(), request.getBaseTexture(), request.getOverlayTexture()).getDynamic());
        }
    }
}
