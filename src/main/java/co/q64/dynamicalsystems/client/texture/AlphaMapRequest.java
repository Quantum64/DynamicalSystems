package co.q64.dynamicalsystems.client.texture;

import com.google.auto.factory.AutoFactory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;

@Getter
@AutoFactory
@EqualsAndHashCode
public class AlphaMapRequest {
    private ResourceLocation generatedTexture, baseTexture, overlayTexture;

    protected AlphaMapRequest(ResourceLocation generatedTexture, ResourceLocation baseTexture, ResourceLocation overlayTexture) {
        this.generatedTexture = generatedTexture;
        this.baseTexture = baseTexture;
        this.overlayTexture = overlayTexture;
    }
}
