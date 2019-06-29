package co.q64.dynamicalsystems.recipe;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class Recipes {
    protected @Inject co.q64.dynamicalsystems.recipe.SimpleRecipeFactory simpleRecipeFactory;

    private Map<RecipeType, List<Recipe>> types = new HashMap<>();

    protected @Inject Recipes() {}

    public RecipeBuilder add(RecipeType... types) {
        return simpleRecipeFactory.create(Arrays.stream(types).collect(Collectors.toSet()));
    }

    protected void apply(SimpleRecipe builder) {
        getTypes(builder.getTypes()).forEach(list -> list.add(builder));
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

    private List<List<Recipe>> getTypes(Set<RecipeType> types) {
        return types.stream().map(this::get).collect(Collectors.toList());
    }
}
