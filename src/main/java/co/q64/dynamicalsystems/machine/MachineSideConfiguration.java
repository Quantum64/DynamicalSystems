package co.q64.dynamicalsystems.machine;

import net.minecraft.util.IStringSerializable;

public enum MachineSideConfiguration implements IStringSerializable {
    FRONT, INPUT, OUTPUT, BOTH, DISABLED;

    @Override
    public String getName() {
        return name().toLowerCase();
    }
}
