package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class MaterialFluid extends Material {
    public MaterialFluid(List<Class<? extends Material>> types) {
        super(Stream.concat(types.stream(), Arrays.asList(MaterialFluid.class).stream()).collect(Collectors.toList()));
    }
}
