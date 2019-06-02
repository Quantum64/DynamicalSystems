package co.q64.dynamicalsystems.tile;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.dynamicalsystems.grid.GridManager;
import co.q64.dynamicalsystems.grid.GridTile;
import net.minecraft.util.math.Direction;

@AutoFactory
public class CableTile extends GridTile {

	public CableTile(@Provided GridManager gridManager) {
		//TODO fix
		super(null, gridManager);
	}

	@Override
	public boolean canAccept(Direction direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canProvide(Direction direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canConnect(Direction direction) {
		// TODO Auto-generated method stub
		return false;
	}

}
