package co.q64.dynamicalsystems.loader.component;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.material.ComponentLoader;
import co.q64.dynamicalsystems.material.components.IngotComponent;
import co.q64.dynamicalsystems.recipe.RecipeType;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CraftingComponentLoader extends ComponentLoader {
    @Inject
    protected CraftingComponentLoader() {}

    @Inject
    protected void init() {

        add(IngotComponent.class, (component, material) -> {
            recipes.add(RecipeType.CRAFTING)
                    .withInput(components.ingot, material).replicate(8)
                    .withOutput(components.block, material).apply();
            recipes.add(RecipeType.CRAFTING)
                    .withInput(components.ingot, material).craftingIdentifier("I")
                    .withInput(components.stick, material).craftingIdentifier("S")
                    .withOutput(components.screw, material)
                    .withPattern("I ", " S").apply();
            recipes.add(RecipeType.SMELTING)
                    .withInput(components.stoneOre, material)
                    .withOutput(components.ingot, material).apply();
            recipes.add(RecipeType.PULVERIZING)
                    .withInput(components.stoneOre, material)
                    .withOutput(components.dust, material).amount(2)
                    .withVoltage(Voltage.STEAM).apply();
        });
    }
}
