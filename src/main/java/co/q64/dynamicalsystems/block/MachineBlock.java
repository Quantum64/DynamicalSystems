package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class MachineBlock extends BaseBlock {
    private MaterialBlockItem item;

    public MachineBlock(String name, Properties settings) {
        super(name, settings);
    }

    public MachineBlock(String name) {
        super(name);
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
