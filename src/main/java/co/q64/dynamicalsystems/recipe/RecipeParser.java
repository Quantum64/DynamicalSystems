package co.q64.dynamicalsystems.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.binders.ConstantBinders.Name;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.unification.SharedItem;
import co.q64.dynamicalsystems.unification.SharedItemStack;
import co.q64.dynamicalsystems.unification.Unification;
import co.q64.dynamicalsystems.util.ItemIdentifierUtil;
import co.q64.dynamicalsystems.util.identifier.IdentifierUtil;
import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

@Singleton
public class RecipeParser {
	protected @Inject IdentifierUtil identifierUtil;
	protected @Inject ItemIdentifierUtil itemIdentifierUtil;
	protected @Inject Unification unification;
	protected @Inject @Name String modName;

	private @Getter List<Recipe<?>> recipes = new ArrayList<>();

	protected @Inject RecipeParser() {}

	protected void addCrafting(Object... data) {
		List<String> pattern = new ArrayList<>();
		List<SharedItemStack> items = new ArrayList<>();
		for (Iterator<Object> itr = Arrays.stream(data).iterator(); itr.hasNext();) {
			Object object = itr.next();
			if (object instanceof String) {
				pattern.add((String) object);
			} else if (object instanceof SharedItem) {
				items.add(unification.getStack((SharedItem) object));
			} else if (object instanceof Item) {
				items.add(unification.getStack((Item) object));
			} else if (object instanceof Component) {
				Component component = (Component) object;
				Material material = null;
				if (itr.hasNext()) {
					Object next = itr.next();
					if (next instanceof Material) {
						material = (Material) next;
					}
				}
				if (material == null) {
					throw new IllegalArgumentException("Component '" + component.getName() + "' must be followed by a material!");
				}
				items.add(unification.getStack(component, material));
			}
		}
		if (items.size() < 2) {
			throw new IllegalStateException("One or fewer items in recipe!");
		}
		SharedItemStack result = items.get(items.size() - 1);
		Identifier recipeId = identifierUtil.get("crafting_" + itemIdentifierUtil.getSharedIdentifier(result.getItem()).getPath());
		ItemStack output = result.getItemStack();
		items.remove(items.size() - 1);
		List<Ingredient> inputs = new ArrayList<>();
		for (SharedItemStack shared : items) {
			inputs.add(shared.toIngredient());
		}
		if (inputs.size() > 9) {
			throw new IllegalStateException("More than 9 inputs in recipe!");
		}
		if (!(pattern.size() == 2 || pattern.size() == 3 || pattern.size() == 0)) {
			throw new IllegalArgumentException("Pattern must contain zero, two, or three strings!");
		}
		if (pattern.size() == 0) {
			DefaultedList<Ingredient> shapelessInputs = DefaultedList.create();
			shapelessInputs.addAll(inputs);
			ShapelessRecipe recipe = new ShapelessRecipe(recipeId, modName, output, shapelessInputs);
			recipes.add(recipe);
			return;
		}
		boolean small = pattern.size() == 2;
		DefaultedList<Ingredient> shapedInputs = DefaultedList.create();
		for (String row : pattern) {
			if (small && row.length() != 2) {
				throw new IllegalArgumentException("Pattern row '" + row + "' does not have two charachers in a small recipe.");
			} else {
				if (row.length() != 3) {
					throw new IllegalArgumentException("Pattern row '" + row + "' does not have three charachers in a standard recipe.");
				}
			}
			for (char c : row.toCharArray()) {
				if (c == ' ') {
					shapedInputs.add(Ingredient.EMPTY);
				} else {
					try {
						int value = Integer.parseInt(String.valueOf(c));
						if (inputs.size() <= value) {
							throw new IllegalArgumentException("Invalid input index in pattern: " + value);
						}
						shapedInputs.add(inputs.get(value));
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("Illegal character in pattern: " + c);
					}
				}
			}
		}
		ShapedRecipe recipe = new ShapedRecipe(recipeId, modName, small ? 2 : 3, small ? 2 : 3, shapedInputs, output);
		recipes.add(recipe);
	}
}
