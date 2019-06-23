package co.q64.dynamicalsystems.client.model;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;

public interface CustomModel extends IUnbakedModel, IBakedModel {
    public String getId();

    @Override
    public default boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public default boolean isGui3d() {
        return true;
    }

    @Override
    public default boolean isBuiltInRenderer() {
        return false;
    }
}
