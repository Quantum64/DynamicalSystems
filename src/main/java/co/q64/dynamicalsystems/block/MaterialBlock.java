package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class MaterialBlock extends BaseBlock {
    private MaterialBlockItem item;
    private BlockRenderLayer renderLayer;

    public MaterialBlock(String id, Component component) {
        super(id, Properties.create(component instanceof ComponentOre ? Material.ROCK : Material.IRON)
                .sound(component instanceof ComponentOre ? SoundType.STONE : SoundType.METAL)
                .hardnessAndResistance(1.5f, 6.0f));
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
