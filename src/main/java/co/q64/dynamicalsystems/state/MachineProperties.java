package co.q64.dynamicalsystems.state;

import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelProperty;

import java.util.Arrays;
import java.util.List;

public interface MachineProperties {
    public static final ModelProperty<MachineSideConfiguration> NORTH = new ModelProperty<>();
    public static final ModelProperty<MachineSideConfiguration> EAST = new ModelProperty<>();
    public static final ModelProperty<MachineSideConfiguration> SOUTH = new ModelProperty<>();
    public static final ModelProperty<MachineSideConfiguration> WEST = new ModelProperty<>();
    public static final ModelProperty<MachineSideConfiguration> UP = new ModelProperty<>();
    public static final ModelProperty<MachineSideConfiguration> DOWN = new ModelProperty<>();
    public static final List<ModelProperty<MachineSideConfiguration>> SIDES = Arrays.asList(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    public static final ModelProperty<Boolean> RUNNING = new ModelProperty<>();
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static ModelProperty<MachineSideConfiguration> get(Direction direction) {
        switch (direction) {
            case DOWN:
                return DOWN;
            case UP:
                return UP;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            case EAST:
                return EAST;
            default:
                return NORTH;
        }
    }


    public static Direction getDirection(ModelProperty<MachineSideConfiguration> property) {
        if (property == EAST) {
            return Direction.EAST;
        } else if (property == SOUTH) {
            return Direction.SOUTH;
        } else if (property == WEST) {
            return Direction.WEST;
        } else if (property == UP) {
            return Direction.UP;
        } else if (property == DOWN) {
            return Direction.DOWN;
        }
        return Direction.NORTH;
    }

    @OnlyIn(Dist.CLIENT)
    public static ModelRotation getRotation(ModelProperty<MachineSideConfiguration> property) {
        if (property == EAST) {
            return ModelRotation.X0_Y90;
        } else if (property == SOUTH) {
            return ModelRotation.X0_Y180;
        } else if (property == WEST) {
            return ModelRotation.X0_Y270;
        } else if (property == UP) {
            return ModelRotation.X90_Y0;
        } else if (property == DOWN) {
            return ModelRotation.X270_Y0;
        }
        return ModelRotation.X0_Y0;
    }
}
