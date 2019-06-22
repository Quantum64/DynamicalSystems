package co.q64.dynamicalsystems.grid.energy;

import co.q64.dynamicalsystems.grid.Grid;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

@AutoFactory
public class EnergyGrid extends Grid {

    protected EnergyGrid(@Provided GridRouteFactoryFactory routeFactory, @Provided GridConnectionFactoryFactory connectionFactory) {
        super(routeFactory.getFactory(), connectionFactory.getFactory());
    }

}
