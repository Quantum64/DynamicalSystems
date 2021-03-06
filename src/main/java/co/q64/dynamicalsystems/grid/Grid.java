package co.q64.dynamicalsystems.grid;

import lombok.Getter;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Grid {
    private static final Direction[] DIRECTIONS = Direction.values();
    private GridRouteFactory routeFactory;
    private GridConnectionFactory connectionFactory;

    private boolean needsRecalculation = false;
    private Map<GridTile, BlockPos> tileLocations = new HashMap<>();
    private Map<GridConnection, GridRoute> routes;
    private @Getter Set<GridTile> tiles = new HashSet<GridTile>();
    private GridTile master;

    protected Grid(GridRouteFactory routeFactory, GridConnectionFactory connectionFactory) {
        this.routeFactory = routeFactory;
        this.connectionFactory = connectionFactory;
        flush();
    }

    public void flush() {
        needsRecalculation = true;
    }

    public void add(GridTile tile) {
        tiles.add(tile);
        tileLocations.put(tile, tile.getPos());
    }

    public void clear() {
        tiles.clear();
        tileLocations.clear();
    }

    public void tick(GridTile tile) {
        if (master == null || !tiles.contains(master)) {
            master = tile;
        }
        if (tile != master) {
            return;
        }
        for (Iterator<GridTile> itr = tiles.iterator(); itr.hasNext(); ) {
            GridTile current = itr.next();
            if (current.isRemoved() /*|| !current.getWorld().isChunkLoaded(current.getPos().getX(), current.getPos().getZ())*/) {
                itr.remove();
                flush();
            }
        }

        if (needsRecalculation) {
            recalculateRoutes();
        }
    }

    private void recalculateRoutes() {
        needsRecalculation = false;
        routes.clear();
        Set<GridConnection> providers = new HashSet<>(), acceptors = new HashSet<>();
        for (GridTile tile : tiles) {
            for (Direction direction : DIRECTIONS) {
                if (tile.canAccept(direction)) {
                    acceptors.add(connectionFactory.create(tile, direction));
                }
                if (tile.canProvide(direction)) {
                    providers.add(connectionFactory.create(tile, direction));
                }
            }
        }
        for (GridConnection provider : providers) {
            for (GridConnection acceptor : acceptors) {
                List<GridTile> transport = new ArrayList<GridTile>();
                routes.put(provider, routeFactory.create(provider, acceptor, transport)); //TODO fix me
            }
        }
    }

    @Singleton
    public static class GridRouteFactoryFactory {
        protected @Inject GridRouteFactoryFactory() {}

        protected @Getter @Inject GridRouteFactory factory;
    }

    @Singleton
    public static class GridConnectionFactoryFactory {
        protected @Inject GridConnectionFactoryFactory() {}

        protected @Getter @Inject GridConnectionFactory factory;
    }
}
