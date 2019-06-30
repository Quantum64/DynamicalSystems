package co.q64.dynamicalsystems.recipe;

import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;

import java.util.function.Supplier;

@AutoFactory
public class RecipeOutput implements RecipeComponent {
    private Supplier<Tag<Item>> tag;
    private Item item;
    private @Getter @Setter int amount = 1;
    private @Getter @Setter float chance;

    protected RecipeOutput(Item item) {
        this.item = item;
    }

    protected RecipeOutput(Supplier<Tag<Item>> tag) {
        this.tag = tag;
    }

    @Override
    public Item getItem() {
        if (item != null) {
            return item;
        }
        return getTag().getAllElements().iterator().next();
    }

    public ItemStack getStack() {
        if (hasItem()) {
            return new ItemStack(item, amount);
        }
        if (hasTag()) {
            return new ItemStack(getTag().getAllElements().iterator().next(), amount);
        }
        return ItemStack.EMPTY;
    }

    public boolean hasTag() {
        return tag != null;
    }

    public boolean hasItem() {
        return item != null;
    }

    public boolean isItem() {
        return hasTag() || hasItem();
    }

    @Override
    public Tag<Item> getTag() {
        return tag.get();
    }
}
