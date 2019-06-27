package co.q64.dynamicalsystems.item;

import co.q64.dynamicalsystems.block.MaterialBlock;
import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import net.minecraft.item.Item;

public interface MaterialItem {
    public Component getComponent();

    public Material getMaterial();

    public String getId();

    public default boolean isBlock() {
        return this instanceof MaterialBlockItem;
    }

    public default boolean isItem() {
        return this instanceof SimpleMaterialItem;
    }

    public default Item getBaseItem() {
        return (Item) this;
    }

    public default SimpleMaterialItem getMaterialItem() {
        return (SimpleMaterialItem) this;
    }

    public default MaterialBlockItem getBlockItem() {
        return (MaterialBlockItem) this;
    }

    public default MaterialBlock getBaseBlock() {
        return ((MaterialBlockItem) this).getBaseBlock();
    }
}
