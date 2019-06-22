package co.q64.dynamicalsystems.mixin.client;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Sprite.class)
public interface SpriteMixin {
    public @Accessor NativeImage[] getImages();
}
