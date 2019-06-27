package co.q64.dynamicalsystems.block.extra;

import co.q64.dynamicalsystems.resource.MultipartBuilder;
import lombok.Getter;
import net.minecraft.util.BlockRenderLayer;

import javax.inject.Inject;
import java.util.Optional;

@Getter
public abstract class BlockDefinition {
    protected @Inject ExtraBlockRegistry registry;

    protected String name = "Unnamed Block";
    protected Optional<MultipartBuilder> stateBuilder = Optional.empty();
    protected BlockRenderLayer renderLayer = BlockRenderLayer.SOLID;

    @Inject
    protected void register() {
        registry.register(this);
    }
}
