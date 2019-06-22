package co.q64.dynamicalsystems.grid;

import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.util.math.Direction;

@Getter
@AutoFactory
public class GridConnection {
    private GridTile tile;
    private Direction direction;

    protected GridConnection(GridTile tile, Direction direction) {
        this.tile = tile;
        this.direction = direction;
    }
}
