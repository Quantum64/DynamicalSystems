package co.q64.dynamicalsystems.util.state;

import co.q64.dynamicalsystems.qualifier.PropertyQualifiers.Down;
import co.q64.dynamicalsystems.qualifier.PropertyQualifiers.East;
import co.q64.dynamicalsystems.qualifier.PropertyQualifiers.North;
import co.q64.dynamicalsystems.qualifier.PropertyQualifiers.South;
import co.q64.dynamicalsystems.qualifier.PropertyQualifiers.Up;
import co.q64.dynamicalsystems.qualifier.PropertyQualifiers.West;
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
}
