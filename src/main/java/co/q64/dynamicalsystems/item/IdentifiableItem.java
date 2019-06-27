package co.q64.dynamicalsystems.item;

import net.minecraft.item.Item;

import java.util.Optional;

public interface IdentifiableItem {
    public String getId();

    public Optional<String> getTag();

    public default Item item() {
        return (Item) this;
    }
}
