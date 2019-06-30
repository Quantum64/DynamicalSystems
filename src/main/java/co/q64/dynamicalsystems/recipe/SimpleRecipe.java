package co.q64.dynamicalsystems.recipe;

import co.q64.dynamicalsystems.block.item.BaseBlockItem;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.unification.Unification;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@AutoFactory
public class SimpleRecipe implements Recipe, RecipeBuilder {
    private @Getter List<RecipeInput> inputs = new ArrayList<>();
    private @Getter List<RecipeOutput> outputs = new ArrayList<>();
    private @Getter int itemInputs, itemOutputs;
    private @Getter String[] pattern;
    private @Getter Voltage minimumVoltage = Voltage.MANUAL;
    private @Getter Set<RecipeType> types;
    private RecipeComponent lastComponent;
    private RecipeInput lastInput;
    private RecipeOutput lastOutput;

    private boolean useFastMatching = true;

    protected SimpleRecipe(
            Set<RecipeType> types,
            @Provided Unification unification, @Provided Recipes recipes,
            @Provided co.q64.dynamicalsystems.recipe.RecipeInputFactory inputFactory,
            @Provided co.q64.dynamicalsystems.recipe.RecipeOutputFactory outputFactory) {
        this.types = types;
        this.recipes = recipes;
        this.unification = unification;
        this.inputFactory = inputFactory;
        this.outputFactory = outputFactory;
    }

    @Override
    public RecipeBuilder withInput(Item item) {
        if (item instanceof BaseItem) {
            return withInput(inputFactory.create(() -> unification.get((BaseItem) item)));
        }
        if (item instanceof BaseBlockItem) {
            return withInput(inputFactory.create(() -> unification.get((BaseBlockItem) item)));
        }
        return withInput(inputFactory.create(item));
    }

    @Override
    public RecipeBuilder withInput(ResourceLocation tag) {
        return withInput(inputFactory.create(() -> unification.get(tag)));
    }

    @Override
    public RecipeBuilder withInput(Component component, Material material) {
        return withInput(inputFactory.create(() -> unification.get(component, material)));
    }

    @Override
    public RecipeBuilder withInput(RecipeInput input) {
        lastComponent = input;
        lastInput = input;
        inputs.add(input);
        return this;
    }

    @Override
    public RecipeBuilder withOutput(Item item) {
        if (item instanceof BaseItem) {
            return withOutput(outputFactory.create(() -> unification.get((BaseItem) item)));
        }
        if (item instanceof BaseBlockItem) {
            return withOutput(outputFactory.create(() -> unification.get((BaseBlockItem) item)));
        }
        return withOutput(outputFactory.create(item));
    }

    @Override
    public RecipeBuilder withOutput(ResourceLocation tag) {
        return withOutput(outputFactory.create(() -> unification.get(tag)));
    }

    @Override
    public RecipeBuilder withOutput(Component component, Material material) {
        return withOutput(outputFactory.create(() -> unification.get(component, material)));
    }

    @Override
    public RecipeBuilder withOutput(RecipeOutput output) {
        lastComponent = output;
        lastOutput = output;
        outputs.add(output);
        return this;
    }

    @Override
    public RecipeBuilder amount(int amount) {
        lastComponent().setAmount(amount);
        return this;
    }

    @Override
    public RecipeBuilder chance(float chance) {
        lastOutput().setChance(chance);
        return this;
    }

    @Override
    public RecipeBuilder craftingIdentifier(String identifier) {
        lastInput().setCraftingIdentifier(identifier);
        return this;
    }

    private RecipeInput lastInput() {
        if (lastInput == null) {
            throw new IllegalStateException("Malformed recipe");
        }
        return lastInput;
    }

    private RecipeOutput lastOutput() {
        if (lastOutput == null) {
            throw new IllegalStateException("Malformed recipe");
        }
        return lastOutput;
    }

    private RecipeComponent lastComponent() {
        if (lastComponent == null) {
            throw new IllegalStateException("Malformed recipe");
        }
        return lastComponent;
    }

    @Override
    public RecipeBuilder withPattern(String... pattern) {
        this.pattern = pattern;
        return this;
    }

    @Override
    public RecipeBuilder withVoltage(Voltage voltage) {
        this.minimumVoltage = voltage;
        return this;
    }

    @Override
    public RecipeBuilder replicate() {
        return replicate(1);
    }

    @Override
    public RecipeBuilder replicate(int times) {
        RecipeComponent component = lastComponent();
        for (int i = 0; i < times; i++) {
            if (component instanceof RecipeInput) {
                withInput((RecipeInput) component);
            } else if (component instanceof RecipeOutput) {
                withOutput((RecipeOutput) component);
            }
        }
        return this;
    }

    @Override
    public boolean matches(List<ItemStack> items, int inputSlots) {
        return matches(items, Voltage.MANUAL, inputSlots);
    }

    @Override
    public RecipeInput getInput() {
        return lastInput();
    }

    @Override
    public RecipeOutput getOutput() {
        return lastOutput();
    }

    @Override
    public boolean matches(List<ItemStack> slots, Voltage voltage, int inputSlots) {
        if (voltage.tier() < getMinimumVoltage().tier()) {
            return false;
        }
        if (useFastMatching) {
            return fastMatches(slots, inputSlots);
        }
        /*
        List<RecipeInput> inputsCopy = new ArrayList<>(getInputs());
        List<Item> itemsCopy = new ArrayList<>(items.size());
        for (ItemStack stack : items) {
            itemsCopy.add(stack.getItem());
        }
        for (Iterator<RecipeInput> inputItr = inputsCopy.iterator(); inputItr.hasNext(); ) {
            RecipeInput input = inputItr.next();
            boolean matched = false;
            for (Iterator<Item> itemItr = itemsCopy.iterator(); itemItr.hasNext(); ) {
                Item item = itemItr.next();
                if (input.matches(item.getItem())) {
                    matched = true;
                    inputItr.remove();
                    itemItr.remove();
                }
            }
            if (!matched) {
                return false;
            }
        }
        */
        return false;
    }

    private boolean fastMatches(List<ItemStack> slots, int inputSlots) {
        List<RecipeInput> inputsCopy = new ArrayList<>(getInputs());
        for (int i = 0; i < inputSlots; i++) {
            ItemStack item = slots.get(i);
            if (item.isEmpty()) {
                continue;
            }
            boolean matched = false;
            for (Iterator<RecipeInput> itr = inputsCopy.iterator(); itr.hasNext(); ) {
                RecipeInput input = itr.next();
                if (input.matches(item.getItem())) {
                    matched = true;
                    itr.remove();
                }
            }
            if (!matched) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canProcess(List<ItemStack> slots, int inputSlots) {
        int itemInputs = 0;
        for (int i = 0; i < inputSlots; i++) {
            if (slots.get(i) != ItemStack.EMPTY) {
                itemInputs++;
            }
        }
        List<RecipeInput> inputsCopy = new ArrayList<>(getInputs());
        int inputsMatched = 0;
        for (int i = 0; i < inputSlots; i++) {
            ItemStack item = slots.get(i);
            if (item.isEmpty()) {
                continue;
            }
            boolean matched = false;
            for (Iterator<RecipeInput> itr = inputsCopy.iterator(); itr.hasNext(); ) {
                RecipeInput input = itr.next();
                if (input.matches(item.getItem())) {
                    if (item.getCount() >= input.getAmount()) {
                        matched = true;
                        itr.remove();
                        inputsMatched++;
                    }
                }
            }
            if (!matched) {
                return false;
            }
        }
        if (inputsMatched != itemInputs || !inputsCopy.isEmpty()) {
            return false;
        }
        int openOutputSlots = 0;
        checkNextOutputSlot:
        for (int i = inputSlots; i < slots.size(); i++) {
            if (openOutputSlots >= outputs.size()) {
                break;
            }
            ItemStack stack = slots.get(i);
            if (stack.isEmpty()) {
                openOutputSlots++;
                continue;
            }
            for (RecipeOutput output : getOutputs()) {
                if (output.isItem()) {
                    if (output.getItem() == stack.getItem()) {
                        if (stack.getCount() + output.getAmount() <= stack.getMaxStackSize()) {
                            openOutputSlots++;
                            continue checkNextOutputSlot;
                        }
                    }
                }
            }
        }
        if (openOutputSlots >= outputs.size()) {
            return true;
        }
        return false;
    }

    @Override
    public void process(List<ItemStack> slots, int inputSlots, BiConsumer<Integer, ItemStack> updateSlots) {
        List<RecipeInput> inputsCopy = new ArrayList<>(getInputs());
        for (int i = 0; i < inputSlots; i++) {
            ItemStack stack = slots.get(i);
            for (Iterator<RecipeInput> itr = inputsCopy.iterator(); itr.hasNext(); ) {
                RecipeInput input = itr.next();
                if (input.matches(stack.getItem()) && stack.getCount() >= input.getAmount()) {
                    stack.shrink(input.getAmount());
                    if (stack.isEmpty()) {
                        stack = ItemStack.EMPTY;
                    }
                    updateSlots.accept(i, stack);
                    itr.remove();
                }
            }
        }
        List<ItemStack> outputSlots = new ArrayList<>(slots.size() - inputSlots);
        for (int i = inputSlots; i < slots.size(); i++) {
            outputSlots.add(slots.get(i));
        }
        nextOutput:
        for (RecipeOutput output : getOutputs()) {
            for (ItemStack stack : outputSlots) {
                if (stack.getItem() == output.getItem() && stack.getCount() + output.getAmount() <= stack.getMaxStackSize()) {
                    stack.grow(output.getAmount());
                    continue nextOutput;
                }
            }
            for (ListIterator<ItemStack> itr = outputSlots.listIterator(); itr.hasNext(); ) {
                ItemStack stack = itr.next();
                if (stack.isEmpty()) {
                    itr.set(output.getStack());
                    continue nextOutput;
                }
            }

        }
        for (int i = inputSlots; i < slots.size(); i++) {
            updateSlots.accept(i, outputSlots.get(i - inputSlots));
        }
    }

    @Override
    public void apply() {
        if (getTypes().size() == 0) {
            throw new IllegalStateException("Malformed recipe - No types");
        }
        if (getOutputs().size() == 0) {
            throw new IllegalStateException("Malformed recipe - No output");
        }
        if (getInputs().size() == 0) {
            throw new IllegalStateException("Malformed recipe - No input");
        }
        if (pattern != null) {
            getInputs().stream().map(RecipeInput::getCraftingIdentifier).filter(x -> x == null).forEach(x -> {throw new IllegalStateException("Malformed recipe - Missing crafting identifier");});
            if (pattern.length > 3) {
                throw new IllegalStateException("Malformed recipe - Too many pattern rows");
            } else if (pattern.length == 0) {
                throw new IllegalStateException("Malformed recipe - No pattern rows");
            }
            Set<Integer> lengths = Arrays.stream(pattern).map(x -> x.length()).collect(Collectors.toSet());
            if (lengths.size() != 1) {
                throw new IllegalStateException("Malformed recipe - Pattern rows not of equal length");
            }
            if (lengths.iterator().next() < 1 || lengths.iterator().next() > 3) {
                throw new IllegalStateException("Malformed recipe - Pattern rows invalid length");
            }
        }
        this.itemInputs = Math.toIntExact(getInputs().stream().filter(RecipeInput::isItem).count());
        this.itemOutputs = Math.toIntExact(getOutputs().stream().filter(RecipeOutput::isItem).count());
        recipes.apply(this);
        this.inputFactory = null;
        this.outputFactory = null;
        this.recipes = null;
    }

    @Override
    public void calculateCanFastMatch() {
        useFastMatching = true;
        for (RecipeInput input : inputs) {
            for (RecipeInput test : inputs) {
                if (input == test) {
                    continue;
                }
                List<Item> inputItems = new ArrayList<>();
                if (input.hasTag()) {
                    for (Item item : input.getTag().getAllElements()) {
                        if (test.matches(item)) {
                            useFastMatching = false;
                            return;
                        }
                    }
                }
                if (input.hasItem()) {
                    if (test.matches(input.getItem())) {
                        useFastMatching = false;
                        return;
                    }
                }
            }
        }
    }

    private Recipes recipes;
    private Unification unification;
    private co.q64.dynamicalsystems.recipe.RecipeInputFactory inputFactory;
    private co.q64.dynamicalsystems.recipe.RecipeOutputFactory outputFactory;
}
