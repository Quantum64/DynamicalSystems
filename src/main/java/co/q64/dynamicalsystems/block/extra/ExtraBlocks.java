package co.q64.dynamicalsystems.block.extra;

import co.q64.dynamicalsystems.block.extra.blocks.TextureDebugBlock;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExtraBlocks {

    public @Inject TextureDebugBlock textureDebugBlock;

    protected @Inject ExtraBlocks() {}


}
