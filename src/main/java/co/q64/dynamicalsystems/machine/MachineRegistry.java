package co.q64.dynamicalsystems.machine;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.AccessLevel;
import lombok.Getter;

@Singleton
public class MachineRegistry {
	private @Getter(AccessLevel.PROTECTED) List<Machine> machines = new ArrayList<>();

	protected @Inject MachineRegistry() {}

	protected void register(Machine machine) {
		machines.add(machine);
	}
}
