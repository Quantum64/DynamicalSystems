package co.q64.dynamicalsystems.recipe;

import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;

@Getter
@AutoFactory
public class RecipeOutput implements RecipeComponent {
    private Tag<Item> tag;
    private Item item;
    private @Setter int amount = 1;
    private @Setter float chance;

    protected RecipeOutput(Item item) {
        this.item = item;
    }

    protected RecipeOutput(Tag<Item> tag) {
        this.tag = tag;
    }

    public ItemStack getStack() {
        if (hasItem()) {
            return new ItemStack(item, amount);
        }
        if (hasTag()) {
            return new ItemStack(tag.getAllElements().iterator().next(), amount);
        }
        return ItemStack.EMPTY;
    }

    public boolean hasTag() {
        return tag != null;
    }

    public boolean hasItem() {
        return item != null;
    }
}
