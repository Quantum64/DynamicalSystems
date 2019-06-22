package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;


public abstract class MaterialLiquid extends MaterialFluid {
    public MaterialLiquid() {
        super(Arrays.asList(MaterialLiquid.class));
    }
}
