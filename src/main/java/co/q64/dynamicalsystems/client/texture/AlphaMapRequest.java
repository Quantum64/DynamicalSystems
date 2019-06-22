package co.q64.dynamicalsystems.client.texture;

import com.google.auto.factory.AutoFactory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AutoFactory
@EqualsAndHashCode
public class AlphaMapRequest {
    private String generatedTexture, baseTexture, overlayTexture;

    protected AlphaMapRequest(String generatedTexture, String baseTexture, String overlayTexture) {
        this.generatedTexture = generatedTexture;
        this.baseTexture = baseTexture;
        this.overlayTexture = overlayTexture;
    }
}
