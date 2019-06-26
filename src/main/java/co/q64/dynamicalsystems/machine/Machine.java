package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import lombok.Getter;

import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
public abstract class Machine {
    protected @Inject MachineRegistry registry;

    protected Predicate<Voltage> generateTier = i -> true;
    protected String name = "Invalid";
    protected String baseTexture = "machine_casing";
    protected String overlayOnTexture = "invalid_on_overlay";
    protected String overlayOffTexture = "invalid_off_overlay";
    protected Function<Voltage, Optional<String>> baseTextureOverride = v -> Optional.empty();
    protected Function<Voltage, Optional<String>> overlayOnTextureOverride = v -> Optional.empty();
    protected Function<Voltage, Optional<String>> overlayOffTextureOverride = v -> Optional.empty();

    @Inject
    protected void register() {
        registry.register(this);
    }

    public String getId() {
        return name.toLowerCase();
    }
}
