package co.q64.dynamicalsystems.mixin.client;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import co.q64.dynamicalsystems.ModInformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

@Mixin(SpriteAtlasTexture.class)
public abstract class SpriteAtlasTextureMixin {
	@Inject(at = @At("HEAD"), method = "loadSprites")
	private void init(ResourceManager manager, Set<Identifier> resources, CallbackInfoReturnable<Collection<Sprite>> info) {
		resources.removeAll(resources.stream().filter(id -> id.getNamespace().equals(ModInformation.ID) && id.getPath().startsWith("generated")).collect(Collectors.toSet()));
	}
}
