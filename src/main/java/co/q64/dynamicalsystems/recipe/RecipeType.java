package co.q64.dynamicalsystems.recipe;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum RecipeType {
    CRAFTING,
    SMELTING,
    PULVERIZING;

    private @Getter List<RecipeType> extensions;

    private RecipeType(RecipeType... extensions) {
        this.extensions = Arrays.asList(extensions);
    }
}
