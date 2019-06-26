package co.q64.dynamicalsystems.client.model;

import co.q64.dynamicalsystems.machine.MachineRegistry;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import lombok.Getter;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.model.IModelState;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MachineSideModels {
    protected @Inject IdentifierUtil identifiers;
    protected @Inject MachineRegistry machineRegistry;

    private @Getter Map<ModelProperty<MachineSideConfiguration>, Map<MachineSideConfiguration, IBakedModel>> components = new HashMap<>();
    private boolean baked = false;

    protected @Inject MachineSideModels() {}

    public void bake(ModelLoader loader) {
        if (baked) {
            throw new IllegalStateException("Machine sides baked twice");
        }
        for (ModelProperty<MachineSideConfiguration> side : MachineProperties.SIDES) {
            Map<MachineSideConfiguration, IBakedModel> sideModels = new HashMap<>();
            for (MachineSideConfiguration type : MachineSideConfiguration.values()) {
                if (type == MachineSideConfiguration.FRONT || type == MachineSideConfiguration.DISABLED) {
                    continue;
                }
                IBakedModel model = loader.getUnbakedModel(identifiers.get(type.getModelName())).bake(loader, ModelLoader.defaultTextureGetter(), new ISprite() {
                    @Override
                    public IModelState getState() {
                        return MachineProperties.getRotation(side);
                    }
                }, DefaultVertexFormats.BLOCK);
                sideModels.put(type, model);
            }
            components.put(side, sideModels);
        }
        baked = true;
    }
}
