package co.q64.dynamicalsystems.recipe;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Set;

public interface Recipe {
    public List<RecipeInput> getInputs();

    public RecipeInput getInput();

    public List<RecipeOutput> getOutputs();

    public RecipeOutput getOutput();

    public boolean matches(Voltage voltage, List<ItemStack> inputs);

    public boolean matches(List<ItemStack> inputs);

    public boolean canProcess(List<ItemStack> inputs);

    public void process(ItemStack[] inputSlots, ItemStack[] outputSlots);

    public void calculateCanFastMatch();

    public String[] getPattern();

    public Voltage getMinimumVoltage();

    public Set<RecipeType> getTypes();
}
