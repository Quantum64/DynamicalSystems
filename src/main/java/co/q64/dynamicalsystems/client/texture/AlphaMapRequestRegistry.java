package co.q64.dynamicalsystems.client.texture;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Getter;

@Singleton
public class AlphaMapRequestRegistry {
	private @Getter Set<AlphaMapRequest> requests = new HashSet<>();

	protected @Inject AlphaMapRequestRegistry() {}

	public void requestAlphaMap(AlphaMapRequest request) {
		requests.add(request);
	}
}
