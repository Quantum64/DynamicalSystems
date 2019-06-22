package co.q64.dynamicalsystems.unification;

import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.item.Item;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AutoFactory
public class SharedItem {
    private @Getter Optional<Component> component = Optional.empty();
    private @Getter Optional<Material> material = Optional.empty();
    private @Getter Optional<Item> first = Optional.empty();
    private @Getter Set<Item> items = new HashSet<Item>();

    protected SharedItem(Component component, Material material, @Provided MaterialItemLoader materialItemLoader) {
        this.component = Optional.of(component);
        this.material = Optional.of(material);
        materialItemLoader.getItem(component, material).ifPresent(item -> unify(item.getItem()));
    }

    protected SharedItem() {}

    public SharedItem unify(Item item) {
        if (items.contains(item)) {
            return this;
        }
        if (item instanceof BaseItem) {
            for (Item unified : items) {
                if (unified instanceof BaseItem) {
                    throw new IllegalStateException("Cannot unify two base items!");
                }
            }
        }
        if (!first.isPresent()) {
            first = Optional.of(item);
        }
        items.add(item);
        return this;
    }
}
