package co.q64.dynamicalsystems.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.auto.factory.AutoFactory;

import lombok.Getter;

@Getter
@AutoFactory
public class GridRoute {
	private int length;
	private GridConnection entry, exit;
	private List<GridTile> transport = new ArrayList<GridTile>();

	protected GridRoute(GridConnection entry, GridConnection exit, List<GridTile> transport) {
		this.entry = entry;
		this.exit = exit;
		this.transport = transport;
		this.length = transport.size() + (entry == exit ? 1 : 2);
	}
}
