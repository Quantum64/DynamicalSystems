package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.block.extra.BlockDefinition;
import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class ExtraBlock extends BaseBlock {
    private BlockRenderLayer renderLayer;

    public ExtraBlock(BlockDefinition definition) {
        super(definition.getName(), Properties.create(Material.IRON).hardnessAndResistance(1.5f, 6.0f));
        this.renderLayer = definition.getRenderLayer();
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return renderLayer;
    }
}
