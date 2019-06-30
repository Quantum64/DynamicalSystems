package co.q64.dynamicalsystems.recipe;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public interface RecipeBuilder {
    public RecipeBuilder withInput(Item item);

    public RecipeBuilder withInput(ResourceLocation tag);

    public RecipeBuilder withInput(Component component, Material material);

    public RecipeBuilder withInput(RecipeInput input);

    public RecipeBuilder withOutput(Item item);

    public RecipeBuilder withOutput(ResourceLocation tag);

    public RecipeBuilder withOutput(Component component, Material material);

    public RecipeBuilder withOutput(RecipeOutput output);

    public RecipeBuilder amount(int amount);

    public RecipeBuilder chance(float chance);

    public RecipeBuilder craftingIdentifier(String identifier);

    public RecipeBuilder withPattern(String... pattern);

    public RecipeBuilder withVoltage(Voltage voltage);

    public RecipeBuilder replicate();

    public RecipeBuilder replicate(int times);

    public void apply();
}
