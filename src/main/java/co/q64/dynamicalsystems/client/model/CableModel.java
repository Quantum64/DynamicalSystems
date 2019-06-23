package co.q64.dynamicalsystems.client.model;

import co.q64.dynamicalsystems.util.IdentifierUtil;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@Singleton
public class CableModel implements CustomModel {
    private final @Getter String id = "block/block_cable";
    protected @Inject IdentifierUtil identifierUtil;

    private ResourceLocation cableCoreIdentifier = new ResourceLocation("dynamicalsystems", "block/cable_core");
    private IUnbakedModel cableCoreUnbakedModel;
    private IBakedModel cableCoreModel;

    protected @Inject CableModel() {}

    @Override
    public Collection<ResourceLocation> getTextures(Function<ResourceLocation, IUnbakedModel> models, Set<String> missingTextureErrors) {
        cableCoreUnbakedModel = models.apply(cableCoreIdentifier);

        Set<ResourceLocation> textures = new HashSet<ResourceLocation>();
        textures.addAll(cableCoreUnbakedModel.getTextures(models, missingTextureErrors));
        return textures;
    }

    @Nullable @Override
    public IBakedModel bake(ModelBakery bakery, Function<ResourceLocation, TextureAtlasSprite> spriteGetter, ISprite sprite, VertexFormat format) {
        cableCoreModel = cableCoreUnbakedModel.bake(bakery, spriteGetter, sprite, new VertexFormat());
        return this;
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction direction, Random random) {
        return cableCoreModel.getQuads(state, direction, random);
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return MissingTextureSprite.func_217790_a();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Arrays.asList(cableCoreIdentifier);
    }
}
