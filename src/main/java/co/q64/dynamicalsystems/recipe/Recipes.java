package co.q64.dynamicalsystems.recipe;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Recipes {
    protected @Inject RecipeParser recipeParser;

    protected @Inject Recipes() {}

    public void addCrafting(Object... data) {
        recipeParser.addCrafting(data);
    }
}
