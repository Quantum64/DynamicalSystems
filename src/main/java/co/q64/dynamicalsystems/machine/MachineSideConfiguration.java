package co.q64.dynamicalsystems.machine;

import co.q64.dynamicalsystems.ModInformation;
import lombok.Getter;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum MachineSideConfiguration implements IStringSerializable {
    FRONT, INPUT, OUTPUT, BOTH, DISABLED;

    private static final @Getter(lazy = true) MachineSideConfiguration[] values = values();

    private @Getter ResourceLocation textureLocation;

    private MachineSideConfiguration() {
        this.textureLocation = new ResourceLocation(ModInformation.ID, getTextureName());
    }

    @Override
    public String getName() {
        return name().toLowerCase();
    }

    public String getModelName() {
        return "block/machine_side_" + getName();
    }

    public String getTextureName() {
        return "block/machine/side_" + getName();
    }
}
