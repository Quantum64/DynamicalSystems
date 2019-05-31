package co.q64.dynamicalsystems.client.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Getter;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelItemPropertyOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ExtendedBlockView;

@Singleton
public class CableModel implements CustomModel {
	private final @Getter String id = "block/block_cable";

	private Mesh mesh;

	protected @Inject CableModel() {}

	@Override
	public Collection<Identifier> getTextureDependencies(Function<Identifier, UnbakedModel> var1, Set<String> var2) {
		return Collections.emptyList();
	}

	@Override
	public void emitBlockQuads(ExtendedBlockView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		//context.getEmitter().square(Direction.NORTH, 1, 1, 5, 5, 1).emit();
		//float sec = System.currentTimeMillis() / 1000f;
		//sec = sec % 5;
		//context.getEmitter().square(Direction.NORTH, 4, 4, 2, 2, sec).emit();
		context.meshConsumer().accept(mesh);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		context.getEmitter().square(Direction.NORTH, 1, 2, 3, 4, 5).emit();
	}

	@Override
	public BakedModel bake(ModelLoader var1, Function<Identifier, Sprite> var2, ModelBakeSettings var3) {
		MeshBuilder mb = RendererAccess.INSTANCE.getRenderer().meshBuilder();
		QuadEmitter emitter = mb.getEmitter();
		emitter.square(Direction.NORTH, 1, 1, 5, 5, 1).emit();
		mesh = mb.build();
		return this;
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
