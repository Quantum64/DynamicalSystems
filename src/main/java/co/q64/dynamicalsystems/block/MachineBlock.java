package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.state.MachineProperties;
import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class MachineBlock extends BaseBlock {
    private Machine machine;
    private Voltage voltage;

    public MachineBlock(String name) {
        super(name, Properties.create(Material.IRON));
        setDefaultState(getStateContainer().getBaseState()
                .with(MachineProperties.NORTH, MachineSideConfiguration.FRONT)
                .with(MachineProperties.SOUTH, MachineSideConfiguration.DISABLED)
                .with(MachineProperties.EAST, MachineSideConfiguration.DISABLED)
                .with(MachineProperties.WEST, MachineSideConfiguration.DISABLED)
                .with(MachineProperties.TOP, MachineSideConfiguration.INPUT)
                .with(MachineProperties.BOTTOM, MachineSideConfiguration.OUTPUT)
                .with(MachineProperties.RUNNING, false));
    }

    public void setMachine(Machine machine, Voltage voltage) {
        this.machine = machine;
        this.voltage = voltage;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState result = getDefaultState();
        Direction front = context.getPlacementHorizontalFacing().getOpposite();
        if (front == Direction.DOWN || front == Direction.UP) {
            throw new IllegalStateException("Invalid horizontal facing");
        }
        for (EnumProperty<MachineSideConfiguration> property : MachineProperties.SIDES) {
            if (MachineProperties.getDirection(property) == front) {
                result = result.with(property, MachineSideConfiguration.FRONT);
            } else {
                result = result.with(property, MachineSideConfiguration.DISABLED);
            }
        }
        return result;
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(MachineProperties.ALL));
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
