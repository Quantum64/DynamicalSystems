package co.q64.dynamicalsystems.block;

import com.google.auto.factory.AutoFactory;

import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import lombok.Getter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;

@Getter
@AutoFactory
public class MachineBlock extends BaseBlock {
	private MaterialBlockItem item;

	public MachineBlock(String name, Settings settings) {
		super(name, settings);
	}

	public MachineBlock(String name) {
		super(name);
	}

	@Environment(EnvType.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
