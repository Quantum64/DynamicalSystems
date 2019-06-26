package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.tile.MachineTileFactory;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class MachineBlock extends BaseBlock {
    private MachineTileFactory tileFactory;
    private Machine machine;
    private Voltage voltage;

    public MachineBlock(String name, @Provided co.q64.dynamicalsystems.tile.MachineTileFactory tileFactory) {
        super(name, Properties.create(Material.IRON));
        this.tileFactory = tileFactory;
        setDefaultState(getStateContainer().getBaseState().with(MachineProperties.FACING, Direction.NORTH));
    }

    public void setMachine(Machine machine, Voltage voltage) {
        this.machine = machine;
        this.voltage = voltage;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getDefaultState().with(MachineProperties.FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(MachineProperties.FACING));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return tileFactory.create();
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
