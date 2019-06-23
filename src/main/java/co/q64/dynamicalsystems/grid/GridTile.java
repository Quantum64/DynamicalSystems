package co.q64.dynamicalsystems.grid;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashSet;
import java.util.Set;

public abstract class GridTile extends TileEntity implements ITickableTileEntity {
    private Set<Direction> disabled = new HashSet<>();

    private @Getter @Setter Grid grid;

    protected GridTile(TileEntityType<?> type, GridManager gridManager) {
        super(type);
        if (!world.isRemote) {
            gridManager.addTileToGridOnLoad(this);
        }
    }

    @OverridingMethodsMustInvokeSuper
    public void tick() {
        grid.tick(this);
    }

    public boolean canAcceptInternal(Direction direction) {
        return !disabled.contains(direction) && canAccept(direction);
    }

    public abstract boolean canAccept(Direction direction);

    public boolean canProvideInternal(Direction direction) {
        return !disabled.contains(direction) && canProvide(direction);
    }

    public abstract boolean canProvide(Direction direction);

    public boolean canConnectInternal(Direction direction) {
        return !disabled.contains(direction) && canProvide(direction);
    }

    public abstract boolean canConnect(Direction direction);

    @Override
    public boolean equals(Object object) {
        return (object instanceof GridTile) && getPos().equals(((GridTile) object).getPos());
    }
}
