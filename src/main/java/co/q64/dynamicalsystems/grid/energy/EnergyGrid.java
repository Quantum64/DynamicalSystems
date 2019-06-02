package co.q64.dynamicalsystems.grid.energy;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.dynamicalsystems.grid.Grid;
import co.q64.dynamicalsystems.grid.GridConnectionFactory;
import co.q64.dynamicalsystems.grid.GridRouteFactory;

@AutoFactory
public class EnergyGrid extends Grid {

	protected EnergyGrid(@Provided GridRouteFactory routeFactory, @Provided GridConnectionFactory connectionFactory) {
		super(routeFactory, connectionFactory);
	}
	
	
}
