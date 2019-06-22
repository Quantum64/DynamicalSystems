package co.q64.dynamicalsystems.client.model;

import co.q64.dynamicalsystems.util.identifier.IdentifierUtil;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelItemPropertyOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

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

    private Identifier cableCoreIdentifier = new Identifier("dynamicalsystems", "block/cable_core");
    private UnbakedModel cableCoreUnbakedModel;
    private BakedModel cableCoreModel;

    protected @Inject CableModel() {}

    @Override
    public Collection<Identifier> getTextureDependencies(Function<Identifier, UnbakedModel> models, Set<String> var2) {
        cableCoreUnbakedModel = models.apply(cableCoreIdentifier);

        Set<Identifier> textures = new HashSet<Identifier>();
        textures.addAll(cableCoreUnbakedModel.getTextureDependencies(models, var2));
        return textures;
    }

    @Override
    public BakedModel bake(ModelLoader loader, Function<Identifier, Sprite> sprites, ModelBakeSettings settings) {
        cableCoreModel = cableCoreUnbakedModel.bake(loader, sprites, settings);
        return this;
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction direction, Random random) {
        return cableCoreModel.getQuads(state, direction, random);
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        return Arrays.asList(cableCoreIdentifier);
    }

    @Override
    public Sprite getSprite() {
        return MissingSprite.getMissingSprite();
    }

    @Override
    public ModelTransformation getTransformation() {
        return ModelTransformation.NONE;
    }

    @Override
    public ModelItemPropertyOverrideList getItemPropertyOverrides() {
        return ModelItemPropertyOverrideList.EMPTY;
    }
}
