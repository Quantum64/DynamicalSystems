package co.q64.dynamicalsystems.block;

import com.google.auto.factory.AutoFactory;

import lombok.Getter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;

@Getter
@AutoFactory
public class CableBlock extends MaterialBlock {

	public CableBlock(String name, Settings settings) {
		super(name, settings);
	}

	public CableBlock(String name) {
		super(name);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
