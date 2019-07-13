package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.client.gui.bar.Bar;
import co.q64.dynamicalsystems.client.gui.bar.DefaultBars;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.recipe.RecipeType;
import co.q64.dynamicalsystems.resource.TranslationService;
import co.q64.dynamicalsystems.util.Point;
import lombok.Getter;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
public abstract class Machine {
    protected @Inject MachineRegistry registry;

    protected RecipeType recipeType = RecipeType.CRAFTING;
    protected Predicate<Voltage> generateTier = i -> true;
    protected String name = "Invalid";
    protected String baseTexture = "machine_casing";
    protected String overlayOnTexture = "invalid_on_overlay";
    protected String overlayOffTexture = "invalid_off_overlay";
    protected Function<Voltage, Optional<String>> baseTextureOverride = v -> Optional.empty();
    protected Function<Voltage, Optional<String>> overlayOnTextureOverride = v -> Optional.empty();
    protected Function<Voltage, Optional<String>> overlayOffTextureOverride = v -> Optional.empty();
    protected List<Bar> bars = Arrays.asList(DefaultBars.ARROW_RIGHT);
    protected List<Point> barLocations = new ArrayList<>(Arrays.asList(new Point(72, 34)));
    protected List<Point> inputSlotLocations = new ArrayList<>();
    protected List<Point> outputSlotLocations = new ArrayList<>();
    protected int guiHeight = 84;

    private ITextComponent translatedName;

    @Inject
    protected void register(TranslationService service) {
        this.translatedName = service.registerTranslation(name);
        registry.register(this);
    }

    public String getId() {
        return name.toLowerCase();
    }
}
