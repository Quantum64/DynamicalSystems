package co.q64.dynamicalsystems.block;

import com.google.auto.factory.AutoFactory;

import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import lombok.Getter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;

@Getter
@AutoFactory
public class MaterialBlock extends BaseBlock {
	private MaterialBlockItem item;
	private BlockRenderLayer renderLayer;

	public MaterialBlock(String name, Settings settings) {
		super(name, settings);
	}

	public MaterialBlock(String name) {
		super(name);
	}

	public void setItem(MaterialBlockItem item) {
		this.item = item;
		this.renderLayer = (item.getComponent().isHasTextureOverlay() || item.getComponent() instanceof ComponentOre) ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
	}

	@Environment(EnvType.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return renderLayer;
	}
}
