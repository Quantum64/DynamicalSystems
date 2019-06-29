package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.recipe.Recipe;
import co.q64.dynamicalsystems.recipe.RecipeInput;
import co.q64.dynamicalsystems.recipe.RecipeOutput;
import co.q64.dynamicalsystems.recipe.RecipeType;
import co.q64.dynamicalsystems.recipe.Recipes;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

@Getter
@AutoFactory
public class MachineGuiLayout {
    private int inputSlots, outputSlots;

    protected MachineGuiLayout(RecipeType type, @Provided Recipes recipes) {
        for (Recipe recipe : recipes.get(type)) {
            inputSlots = Math.max(Math.toIntExact(recipe.getInputs().stream().filter(RecipeInput::isItem).count()), inputSlots);
            outputSlots = Math.max(Math.toIntExact(recipe.getOutputs().stream().filter(RecipeOutput::isItem).count()), outputSlots);
        }
    }
}
