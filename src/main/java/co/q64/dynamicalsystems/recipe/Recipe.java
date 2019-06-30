package co.q64.dynamicalsystems.recipe;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public interface Recipe {
    public List<RecipeInput> getInputs();

    public RecipeInput getInput();

    public List<RecipeOutput> getOutputs();

    public RecipeOutput getOutput();

    public boolean matches(List<ItemStack> slots, Voltage voltage, int inputSlots);

    public boolean matches(List<ItemStack> slots, int inputSlots);

    public boolean canProcess(List<ItemStack> slots, int inputSlots);

    public void process(List<ItemStack> slots, int inputSlots, BiConsumer<Integer, ItemStack> output);

    public void calculateCanFastMatch();

    public String[] getPattern();

    public Voltage getMinimumVoltage();

    public Set<RecipeType> getTypes();
}
