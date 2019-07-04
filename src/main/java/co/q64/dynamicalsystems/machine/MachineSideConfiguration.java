package co.q64.dynamicalsystems.machine;

import lombok.Getter;
import net.minecraft.util.IStringSerializable;

public enum MachineSideConfiguration implements IStringSerializable {
    FRONT, INPUT, OUTPUT, BOTH, DISABLED;

    private static final @Getter(lazy = true) MachineSideConfiguration[] values = values();

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
