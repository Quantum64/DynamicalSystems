package co.q64.dynamicalsystems.recipe;

import co.q64.dynamicalsystems.unification.Unification;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;

import java.util.function.Supplier;

@AutoFactory
public class RecipeInput implements RecipeComponent {
    private Supplier<Tag<Item>> tag;
    private @Getter Item item;
    private @Getter @Setter int amount = 1;
    private @Getter @Setter String craftingIdentifier = null;
    //TODO fluids here

    protected RecipeInput(Item item) {
        this.item = item;
    }

    protected RecipeInput(Supplier<Tag<Item>> tag) {
        this.tag = tag;
    }

    public boolean matches(Item item) {
        return (tag != null && getTag().getAllElements().contains(item)) || (this.item != null && this.item.equals(item));
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

    @Override
    public Tag<Item> getTag() {
        return tag.get();
    }

    public boolean isItem() {
        return hasTag() || hasItem();
    }
}
