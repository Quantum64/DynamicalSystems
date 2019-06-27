package co.q64.dynamicalsystems.block.extra.blocks;

import co.q64.dynamicalsystems.block.extra.BlockDefinition;
import co.q64.dynamicalsystems.resource.MultipartBuilderFactory;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import net.minecraft.util.BlockRenderLayer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TextureDebugBlock extends BlockDefinition {
    protected @Inject TextureDebugBlock(MultipartBuilderFactory stateFactory, IdentifierUtil identifiers) {
        this.name = "Texture Debug Block";
        this.renderLayer = BlockRenderLayer.CUTOUT;
        this.stateBuilder = Optional.of(stateFactory.create()
                .apply(identifiers.get("block/machine_side_input"))
                .apply(identifiers.get("block/machine_side_output"), 90)
                .apply(identifiers.get("block/machine_side_both"), 180));

    }
}
