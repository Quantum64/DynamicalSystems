package co.q64.dynamicalsystems.machine;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.machine.processing.PulverizerMachine;

@Singleton
public class Machines {
	protected @Inject MachineRegistry registry;

	public @Inject PulverizerMachine pulverizer;

	protected @Inject Machines() {}

	public List<Machine> getAll() {
		return registry.getMachines();
	}
}
