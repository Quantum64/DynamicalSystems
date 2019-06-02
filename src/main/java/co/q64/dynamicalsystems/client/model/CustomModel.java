package co.q64.dynamicalsystems.client.model;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.UnbakedModel;

public interface CustomModel extends BakedModel, UnbakedModel {
	public String getId();

	/*
	@Override
	public default boolean isVanillaAdapter() {
		return false;
	}
	*/

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
}
