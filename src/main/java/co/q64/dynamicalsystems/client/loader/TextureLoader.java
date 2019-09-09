package co.q64.dynamicalsystems.client.loader;


import co.q64.dynamicalsystems.qualifier.ConstantQualifiers.ModId;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequest;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.AlphaMapSpriteFactory;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.Logger;
import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
public class TextureLoader {
    protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    protected @Inject AlphaMapSpriteFactory alphaMapSpriteFactory;
    protected @Inject Logger logger;
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject RegistryUtil registryUtil;
    private AtomicBoolean loaded = new AtomicBoolean(false);

    protected @Inject TextureLoader() {}

    public void loadTextures(AtlasTexture map) {
        if (!loaded.getAndSet(true)) {
            long start = System.currentTimeMillis();
            for (AlphaMapRequest request : alphaMapRequestRegistry.getRequests()) {
                registryUtil.registerTexture(request.getGeneratedTexture());
                alphaMapSpriteFactory.create(request.getGeneratedTexture(), request.getBaseTexture(), request.getOverlayTexture()).load(Minecraft.getInstance().getResourceManager());
            }
            logger.info("Processed " + alphaMapRequestRegistry.getRequests().size() + " alphamap requests (" + (System.currentTimeMillis() - start) + " ms)");
        }
    }
}
