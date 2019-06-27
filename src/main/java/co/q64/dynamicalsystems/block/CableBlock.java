package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.util.state.DirectionalPropertyUtil;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class CableBlock extends MaterialBlock {
    private DirectionalPropertyUtil propertyUtil;

    public CableBlock(String id, Component settings, @Provided DirectionalPropertyUtil propertyUtil) {
        super(id, settings);
        this.propertyUtil = propertyUtil;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader view, BlockPos position) {
        return true;
    }
}
