package co.q64.dynamicalsystems.client.texture;

import com.google.auto.factory.AutoFactory;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AutoFactory
@EqualsAndHashCode
@AllArgsConstructor
public class AlphaMapRequest {
	private String generatedTexture, baseTexture, overlayTexture;
}
