package co.q64.dynamicalsystems.mixin.client;

import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpriteAtlasTexture.class)
public abstract class SpriteAtlasTextureMixin {
    //@Inject(at = @At("HEAD"), method = "loadSprites")
    //private void init(ResourceManager manager, Set<Identifier> resources, CallbackInfoReturnable<Collection<Sprite>> info) {
    //	resources.removeAll(resources.stream().filter(id -> id.getNamespace().equals(ModInformation.ID) && id.getPath().startsWith("generated")).collect(Collectors.toSet()));
    //}
}
