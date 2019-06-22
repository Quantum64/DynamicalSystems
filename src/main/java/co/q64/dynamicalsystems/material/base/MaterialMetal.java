package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;

public abstract class MaterialMetal extends MaterialSolid {
    public MaterialMetal() {
        super(Arrays.asList(MaterialMetal.class));
        textureOverrideFamily = "metal";
    }
}
