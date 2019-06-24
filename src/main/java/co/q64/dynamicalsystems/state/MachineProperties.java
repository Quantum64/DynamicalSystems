package co.q64.dynamicalsystems.state;

import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;

public interface MachineProperties {
    public static final EnumProperty<MachineSideConfiguration> NORTH = EnumProperty.create("north", MachineSideConfiguration.class, MachineSideConfiguration.values());
    public static final EnumProperty<MachineSideConfiguration> EAST = EnumProperty.create("east", MachineSideConfiguration.class, MachineSideConfiguration.values());
    public static final EnumProperty<MachineSideConfiguration> SOUTH = EnumProperty.create("south", MachineSideConfiguration.class, MachineSideConfiguration.values());
    public static final EnumProperty<MachineSideConfiguration> WEST = EnumProperty.create("west", MachineSideConfiguration.class, MachineSideConfiguration.values());
    public static final EnumProperty<MachineSideConfiguration> TOP = EnumProperty.create("top", MachineSideConfiguration.class, MachineSideConfiguration.values());
    public static final EnumProperty<MachineSideConfiguration> BOTTOM = EnumProperty.create("bottom", MachineSideConfiguration.class, MachineSideConfiguration.values());
    public static final BooleanProperty RUNNING = BooleanProperty.create("running");
    public static final IProperty<?>[] ALL = new IProperty[]{NORTH, EAST, SOUTH, WEST, TOP, BOTTOM, RUNNING};
    public static final EnumProperty<MachineSideConfiguration>[] FACES = new EnumProperty[]{NORTH, EAST, SOUTH, WEST, TOP, BOTTOM};
    public static final EnumProperty<MachineSideConfiguration>[] SIDES = new EnumProperty[]{NORTH, EAST, SOUTH, WEST};

    public static EnumProperty<MachineSideConfiguration> get(Direction direction) {
        switch (direction) {
            case DOWN:
                return BOTTOM;
            case UP:
                return TOP;
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


    public static Direction getDirection(EnumProperty<MachineSideConfiguration> property) {
        if (property == EAST) {
            return Direction.EAST;
        } else if (property == SOUTH) {
            return Direction.SOUTH;
        } else if (property == WEST) {
            return Direction.WEST;
        } else if (property == TOP) {
            return Direction.UP;
        } else if (property == BOTTOM) {
            return Direction.DOWN;
        }
        return Direction.NORTH;
    }
}
