package co.q64.dynamicalsystems.grid;

import com.google.auto.factory.AutoFactory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
