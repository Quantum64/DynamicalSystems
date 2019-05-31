package co.q64.dynamicalsystems.unification;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.mixin.RecipeManagerMixin;
import co.q64.dynamicalsystems.mixin.TagMixin;
import co.q64.dynamicalsystems.recipe.RecipeParser;
import co.q64.dynamicalsystems.util.TagUtil;
import co.q64.dynamicalsystems.util.identifier.IdentifierUtil;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tag.Tag;
import net.minecraft.tag.Tag.Entry;
import net.minecraft.tag.Tag.TagEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Singleton
public class ServerDataUnifier {
	protected @Inject IdentifierUtil identifierUtil;
	protected @Inject TagUtil tagUtil;
	protected @Inject Unification unification;
	protected @Inject RecipeParser recipeParser;

	private MinecraftServer activeServer;

	protected @Inject ServerDataUnifier() {}

	public void register() {
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {

			@Override
			public void apply(ResourceManager manager) {
				hijackItemTags();
				if (activeServer != null && activeServer.isRunning()) {
					hijackRecipes(activeServer.getRecipeManager());
				}
			}

			@Override
			public Identifier getFabricId() {
				return identifierUtil.get("server_data_unifier");
			}
		});

		ServerStartCallback.EVENT.register(server -> {
			activeServer = server;
			hijackRecipes(server.getRecipeManager());
		});
	}

	private void hijackItemTags() {
		for (SharedItem shared : unification.getItems()) {
			for (Tag<Item> tag : tagUtil.getPossibleTags(shared)) {
				@SuppressWarnings("unchecked") // Impossible
				TagMixin<Item> mixin = (TagMixin<Item>) tag;
				if (mixin.getEntries().size() > 0) {
					throw new IllegalStateException("Something is adding to my tags...");
				}
				Set<Entry<Item>> updated = new HashSet<>(mixin.getEntries());
				for (Item item : shared.getItems()) {
					updated.add(new TagEntry<Item>(Registry.ITEM.getId(item)));
				}
				mixin.setEntries(updated);
			}

		}
	}

	private void hijackRecipes(RecipeManager manager) {
		for (Recipe<?> recipe : recipeParser.getRecipes()) {
			manager.add(recipe);
		}

		for (Recipe<?> recipe : ((RecipeManagerMixin) manager).getRecipeMap().values().stream().map(Map::values).flatMap(Collection::stream).collect(Collectors.toList())) {
			if (recipe instanceof CraftingRecipe) {
				// Just add em'...
				List<Ingredient> inputs = recipe.getPreviewInputs();
				for (int i = 0; i < inputs.size(); i++) {
					Ingredient current = inputs.get(i);
					if (!current.isEmpty()) {
						Set<Item> newInputs = new HashSet<>();
						for (ItemStack item : current.getStackArray()) {
							newInputs.addAll(unification.get(item.getItem()).getItems());
						}
						inputs.set(i, Ingredient.ofItems(newInputs.toArray(new Item[0])));
					}
				}
			}
			if (recipe instanceof AbstractCookingRecipe) {
				// Bit more complex...
			}
		}
	}
}
