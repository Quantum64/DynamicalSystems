package co.q64.dynamicalsystems.client.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public interface CustomModel extends BakedModel, UnbakedModel, FabricBakedModel {
	public String getId();

	@Override
	public default Collection<Identifier> getModelDependencies() {
		return Collections.emptyList();
	}

	@Override
	public default boolean isVanillaAdapter() {
		return false;
	}

	@Override
	public default boolean useAmbientOcclusion() {
		return false;
	}

	@Override
	public default boolean hasDepthInGui() {
		return true;
	}

	@Override
	public default boolean isBuiltin() {
		return false;
	}
	
	@Override
	public default List<BakedQuad> getQuads(BlockState var1, Direction var2, Random var3) {
		return Collections.emptyList();
	}
}
