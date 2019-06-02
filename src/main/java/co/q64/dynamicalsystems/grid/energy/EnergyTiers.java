package co.q64.dynamicalsystems.grid.energy;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EnergyTiers {
	protected @Inject EnergyTiers() {}

	public double getResistanceAtVoltage(Voltage voltage, double base) {
		return Math.pow(voltage.tier() * base, 2) / 1000f;
	}

	public double getVoltageNumaric(Voltage voltage) {
		return Math.pow(2.4, voltage.tier());
	}

	public int getProcessingTicks(Voltage voltage) {
		return (2 << (Voltage.max().tier() - voltage.tier())) / 2;
	}
}
