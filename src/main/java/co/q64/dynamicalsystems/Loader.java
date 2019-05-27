package co.q64.dynamicalsystems;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.dynamicalsystems.material.Materials;

@Singleton
public class Loader {
	protected @Inject Materials materials;

	protected @Inject Loader() {}

	public void load() {

	}
}
