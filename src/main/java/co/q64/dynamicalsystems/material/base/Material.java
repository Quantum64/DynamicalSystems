package co.q64.dynamicalsystems.material.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import co.q64.dynamicalsystems.material.MaterialRegistry;
import lombok.Getter;

@Getter
public abstract class Material {
	protected @Inject MaterialRegistry registry;

	private List<Class<? extends Material>> subclassTypes;

	protected String name = "Unknown";
	protected String textureOverrideFamily = "";
	protected List<Component> textureOverrides = new ArrayList<>();
	protected int color = 0xFFFFFF;

	public Material(List<Class<? extends Material>> subclassTypes) {
		this.subclassTypes = subclassTypes;
	}

	@Inject
	protected void registerMaterial() {
		registry.register(this, Stream.concat(subclassTypes.stream(), Arrays.asList(Material.class).stream()).collect(Collectors.toList()));
		this.subclassTypes = null;
	}
}
