package co.q64.dynamicalsystems.unification;

import com.google.auto.factory.AutoFactory;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

@AutoFactory
public class SharedItemStack {
	private @Getter SharedItem item;
	private @Getter int amount;

	protected SharedItemStack(SharedItem item, int amount) {
		this.item = item;
		this.amount = amount;
		if (!item.getFirst().isPresent()) {
			throw new IllegalStateException("Cannot create SharedItemStack with an empty shared item!");
		}
	}

	protected SharedItemStack(SharedItem item) {
		this(item, 1);
	}

	public ItemStack getItemStack() {
		return new ItemStack(item.getFirst().get(), amount);
	}

	public Ingredient toIngredient() {
		return Ingredient.ofItems(item.getItems().toArray(new Item[0]));
	}
}
