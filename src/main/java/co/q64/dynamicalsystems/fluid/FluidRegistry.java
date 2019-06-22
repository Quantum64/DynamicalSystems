package co.q64.dynamicalsystems.fluid;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FluidRegistry {
    protected @Inject MaterialFluidFactory fluidFactory;

    protected @Inject FluidRegistry() {}

}
