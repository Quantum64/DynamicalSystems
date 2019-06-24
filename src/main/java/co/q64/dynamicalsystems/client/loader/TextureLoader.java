package co.q64.dynamicalsystems.client.loader;

/*
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
*/