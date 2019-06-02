package co.q64.dynamicalsystems.block;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.dynamicalsystems.util.state.DirectionalPropertyUtil;
import lombok.Getter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Getter
@AutoFactory
public class CableBlock extends MaterialBlock {
	private DirectionalPropertyUtil propertyUtil;

	public CableBlock(String name, Settings settings, @Provided DirectionalPropertyUtil propertyUtil) {
		super(name, settings);
		this.propertyUtil = propertyUtil;
	}

	public CableBlock(String name, @Provided DirectionalPropertyUtil propertyUtil) {
		super(name);
		this.propertyUtil = propertyUtil;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isTranslucent(BlockState state, BlockView view, BlockPos position) {
		return true;
	}
}
