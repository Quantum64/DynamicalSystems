package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class MaterialBlock extends BaseBlock {
    private MaterialBlockItem item;
    private BlockRenderLayer renderLayer;

    public MaterialBlock(String name, Properties settings) {
        super(name, settings);
    }

    public MaterialBlock(String name) {
        super(name);
    }

    public void setItem(MaterialBlockItem item) {
        this.item = item;
        this.renderLayer = (item.getComponent().isHasTextureOverlay() || item.getComponent() instanceof ComponentOre) ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return renderLayer;
    }
}
