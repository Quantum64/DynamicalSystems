package co.q64.dynamicalsystems.grid.energy;

public enum Voltage {
    T1(1), T2(2), T3(3), T4(4), T5(5), T6(6), T7(7), T8(8);

    private int tier;

    private Voltage(int tier) {
        this.tier = tier;
    }

    public int tier() {
        return tier;
    }

    public static Voltage max() {
        return T8;
    }
}
