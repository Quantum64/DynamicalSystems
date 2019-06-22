package co.q64.dynamicalsystems.material.base;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.ComponentRegistry;
import lombok.Getter;

import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
public abstract class Component {
    protected @Inject ComponentRegistry componentRegistry;

    protected Optional<Function<Material, MaterialItem>> factory = Optional.empty();
    protected Predicate<Material> generate = material -> false;
    protected String name = "";
    protected String prefix = "";
    protected String textureName = "";
    protected String model = "";
    protected boolean hasTextureOverlay = false;

    protected Component() {}

    @Inject
    protected void register() {
        componentRegistry.register(this);
    }
}
