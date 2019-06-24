package co.q64.dynamicalsystems.unification;

import co.q64.dynamicalsystems.recipe.RecipeParser;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.TagUtil;
import net.minecraft.server.MinecraftServer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServerDataUnifier {
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject TagUtil tagUtil;
    protected @Inject Unification unification;
    protected @Inject RecipeParser recipeParser;

    private MinecraftServer activeServer;

    protected @Inject ServerDataUnifier() {}

    public void register() {
        /*
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
                return identifiers.get("server_data_unifier");
            }
        });

        ServerStartCallback.EVENT.register(server -> {
            activeServer = server;
            hijackRecipes(server.getRecipeManager());
        });
        */

    }

    /*
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

     */
}
