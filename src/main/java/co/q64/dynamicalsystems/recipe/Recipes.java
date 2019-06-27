package co.q64.dynamicalsystems.recipe;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class Recipes {
    protected @Inject co.q64.dynamicalsystems.recipe.SimpleRecipeFactory simpleRecipeFactory;

    private Map<RecipeType, List<Recipe>> types = new HashMap<>();

    @Inject
    protected Recipes() {}

    public RecipeBuilder add(RecipeType type) {
        return simpleRecipeFactory.create(type);
    }

    protected void apply(SimpleRecipe builder) {
        get(builder.getType()).add(builder);
    }

    public List<Recipe> get(RecipeType... types) {
        List<Recipe> result = new ArrayList<>();
        for (RecipeType type : types) {
            result.addAll(get(type));
        }
        return result;
    }

    public List<Recipe> get(RecipeType type) {
        List<Recipe> recipes = types.get(type);
        if (recipes == null) {
            recipes = new ArrayList<>();
            types.put(type, recipes);
        }
        return recipes;
    }
}
