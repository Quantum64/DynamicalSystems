package co.q64.dynamicalsystems.material.base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class MaterialSolid extends Material {
    public MaterialSolid(List<Class<? extends Material>> types) {
        super(Stream.concat(types.stream(), Arrays.asList(MaterialSolid.class).stream()).collect(Collectors.toList()));
    }
}
