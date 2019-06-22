package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;

public abstract class MaterialGem extends MaterialSolid {
    public MaterialGem() {
        super(Arrays.asList(MaterialGem.class));
        textureOverrideFamily = "gem";
    }
}
