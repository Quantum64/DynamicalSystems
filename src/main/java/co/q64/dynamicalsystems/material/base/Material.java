package co.q64.dynamicalsystems.material.base;

import co.q64.dynamicalsystems.material.MaterialRegistry;
import co.q64.dynamicalsystems.type.Element;
import lombok.Getter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public abstract class Material {
    protected @Inject MaterialRegistry registry;

    private List<Class<? extends Material>> subclassTypes;

    protected String name = "Unknown";
    protected String textureOverrideFamily = "";
    protected List<Component> textureOverrides = new ArrayList<>();
    protected Supplier<String> tagPart = () -> name.toLowerCase().replace(" ", "");
    protected int color = 0xFFFFFF;
    protected Optional<Element> element = Optional.empty();

    public Material(List<Class<? extends Material>> subclassTypes) {
        this.subclassTypes = subclassTypes;
    }

    @Inject
    protected void registerMaterial() {
        registry.register(this, Stream.concat(subclassTypes.stream(), Arrays.asList(Material.class).stream()).collect(Collectors.toList()));
        this.subclassTypes = null;
    }
}
