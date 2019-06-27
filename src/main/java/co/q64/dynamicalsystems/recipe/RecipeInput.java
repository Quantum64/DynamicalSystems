package co.q64.dynamicalsystems.recipe;

import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;

@AutoFactory
@Getter
public class RecipeInput implements RecipeComponent {
    private Tag<Item> tag;
    private Item item;
    private @Setter int amount = 1;
    private @Setter String craftingIdentifier = null;
    //TODO fluids here

    protected RecipeInput(Item item) {
        this.item = item;
    }

    protected RecipeInput(Tag<Item> tag) {
        this.tag = tag;
    }

    public boolean matches(Item item) {
        return (tag != null && tag.getEntries().contains(item)) || (this.item != null && this.item.equals(item));
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
