package co.q64.dynamicalsystems.grid;

import co.q64.dynamicalsystems.grid.energy.EnergyGridFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class GridManager /*implements ServerTickCallback*/ {
    private static final Direction[] DIRECTIONS = Direction.values(); // Cache to avoid copy
    protected @Inject EnergyGridFactory gridFactory; //TODO fix

    private List<Grid> grids = new ArrayList<>();

    protected @Inject GridManager() {
        //ServerTickCallback.EVENT.register(this);
    }

    public void addTileToGridOnLoad(GridTile tile) {
        if (tile.getGrid() != null) {
            throw new IllegalStateException("Only on generateModels");
        }

        // Check what grid(s) we are adjacent to
        Set<Grid> adjacent = new HashSet<Grid>(); // Set checks grid reference equality
        for (Direction direction : DIRECTIONS) {
            BlockPos position = tile.getPos().offset(direction);
            if (tile.getWorld().isAreaLoaded(position, 1)) {
                TileEntity blockEntity = tile.getWorld().getTileEntity(position);
                if (blockEntity != null && blockEntity instanceof GridTile) {
                    adjacent.add(((GridTile) blockEntity).getGrid());
                }
            }
        }

        // If there are no adjacent grids, create a new one
        if (adjacent.size() == 0) {
            Grid grid = gridFactory.create();
            grid.add(tile);
            tile.setGrid(grid);
            return;
        }

        // Pick the largest grid as primary so we move less stuff around
        Grid primary = null;
        for (Grid grid : adjacent) {
            if (primary == null || grid.getTiles().size() > primary.getTiles().size()) {
                primary = grid;
            }
        }
        adjacent.remove(primary);

        // Merge other adjacent grids into this one
        primary.add(tile);
        tile.setGrid(primary);
        for (Grid grid : adjacent) {
            for (GridTile move : grid.getTiles()) {
                primary.add(tile);
                move.setGrid(primary);
            }
            grid.clear(); // For bad actors who hold refs to grids
            grid.flush();
        }

        // Finally walk the updated grid
        primary.flush();
    }

    //@Override
    public void tick(MinecraftServer server) {
        // TODO Auto-generated method stub

    }

}
