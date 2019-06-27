package co.q64.dynamicalsystems.recipe;

import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface RecipeComponent {
    public Item getItem();

    public Tag<Item> getTag();

    public void setAmount(int amount);
}
