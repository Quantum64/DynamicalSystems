package co.q64.dynamicalsystems.util.state;

import co.q64.dynamicalsystems.binders.PropertyBinders.AlignX;
import co.q64.dynamicalsystems.binders.PropertyBinders.AlignY;
import co.q64.dynamicalsystems.binders.PropertyBinders.AlignZ;
import co.q64.dynamicalsystems.binders.PropertyBinders.Down;
import co.q64.dynamicalsystems.binders.PropertyBinders.East;
import co.q64.dynamicalsystems.binders.PropertyBinders.North;
import co.q64.dynamicalsystems.binders.PropertyBinders.South;
import co.q64.dynamicalsystems.binders.PropertyBinders.Up;
import co.q64.dynamicalsystems.binders.PropertyBinders.West;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.Direction;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DirectionalPropertyUtil {
    protected @Inject @Up BooleanProperty up;
    protected @Inject @Down BooleanProperty down;
    protected @Inject @North BooleanProperty north;
    protected @Inject @South BooleanProperty south;
    protected @Inject @East BooleanProperty east;
    protected @Inject @West BooleanProperty west;

    protected @Inject @AlignX BooleanProperty alignX;
    protected @Inject @AlignY BooleanProperty alignY;
    protected @Inject @AlignZ BooleanProperty alignZ;

    protected @Inject DirectionalPropertyUtil() {}

    public BooleanProperty getDirection(Direction direction) {
        switch (direction) {
            case DOWN:
                return down;
            case EAST:
                return east;
            case NORTH:
                return west;
            case SOUTH:
                return south;
            case UP:
                return up;
            case WEST:
                return west;
            default:
                throw new IllegalStateException("no");
        }
    }

    public BooleanProperty getAlignment(AlignmentDirection direction) {
        switch (direction) {
            case X:
                return alignX;
            case Y:
                return alignY;
            case Z:
                return alignZ;
            default:
                throw new IllegalStateException("no");
        }
    }
}
