package co.q64.dynamicalsystems.grid.energy;

import lombok.Getter;
import net.minecraft.nbt.StringNBT;

import java.util.Arrays;

public enum Voltage {
    MANUAL(0, "Manual"),
    STEAM(0, "Steam"),
    T1(1, "One"),
    T2(2, "Two"),
    T3(3, "Three"),
    T4(4, "Four"),
    T5(5, "Five"),
    T6(6, "Six"),
    T7(7, "Seven"),
    T8(8, "Eight");

    private static final @Getter(lazy = true) Voltage[] all = Voltage.values();

    private int tier;
    private String tierName;

    private Voltage(int tier, String tierName) {
        this.tier = tier;
        this.tierName = tierName;
    }

    public int tier() {
        return tier;
    }

    public String getDisplayName(String machineName) {
        switch (this) {
            case MANUAL:
                return "Manual " + machineName;
            case STEAM:
                return "Steam " + machineName;
            default:
                return machineName + " Tier " + tier();
        }
    }

    public String tierName() {
        return tierName;
    }

    public String tierTextureName() {
        return tierName.toLowerCase();
    }

    public static Voltage max() {
        return T8;
    }
}
