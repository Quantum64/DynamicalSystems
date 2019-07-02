package co.q64.dynamicalsystems.grid.energy;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EnergyTiers {
    protected @Inject EnergyTiers() {}

    public double getResistanceAtVoltage(Voltage voltage, double base) {
        if (voltage.tier() == 0) {
            return 0;
        }
        return Math.pow(voltage.tier() * base, 2) / 1000f;
    }

    public double getVoltageNumaric(Voltage voltage) {
        if (voltage.tier() == 0) {
            return 0;
        }
        return Math.pow(2.4, voltage.tier());
    }

    public int getProcessingTicks(Voltage voltage) {
        int extraFun = voltage == Voltage.MANUAL ? 2 : 1;
        return ((2 << (Voltage.max().tier() - voltage.tier())) - 1) / 2 * extraFun;
    }
}
