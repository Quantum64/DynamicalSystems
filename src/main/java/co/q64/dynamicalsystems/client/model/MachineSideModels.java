package co.q64.dynamicalsystems.client.model;

import co.q64.dynamicalsystems.client.texture.MachineTextureMap;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineRegistry;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import lombok.Getter;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.model.IModelState;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
public class MachineSideModels {
    protected @Inject IdentifierUtil identifiers;
    protected @Inject MachineRegistry machineRegistry;
    protected @Inject MachineTextureMap textureLoader;

    private @Getter Map<ModelProperty<MachineSideConfiguration>, Map<MachineSideConfiguration, Map<Voltage, IBakedModel>>> sides = new HashMap<>();
    private @Getter Map<ModelProperty<MachineSideConfiguration>, Map<Machine, Map<Voltage, IBakedModel>>> frontOff = new HashMap<>();
    private @Getter Map<ModelProperty<MachineSideConfiguration>, Map<Machine, Map<Voltage, IBakedModel>>> frontOn = new HashMap<>();
    private boolean baked = false;

    protected @Inject MachineSideModels() {}

    public void bake(ModelLoader loader) {
        if (baked) {
            throw new IllegalStateException("Machine sides baked twice");
        }
        Set<String> errors = new HashSet<>();
        for (ModelProperty<MachineSideConfiguration> side : MachineProperties.SIDES) {
            Map<MachineSideConfiguration, Map<Voltage, IBakedModel>> sideModels = new HashMap<>();
            for (MachineSideConfiguration type : MachineSideConfiguration.values()) {
                if (type == MachineSideConfiguration.FRONT) {
                    continue;
                }
                Map<Voltage, IBakedModel> voltageModels = new HashMap<>();
                for (Voltage voltage : Voltage.getAll()) {
                    IUnbakedModel unbaked = loader.getUnbakedModel(textureLoader.getMachineCasingModel(voltage, type));
                    unbaked.getTextures(loader::getUnbakedModel, errors);
                    IBakedModel model = unbaked.bake(loader, ModelLoader.defaultTextureGetter(), new ISprite() {
                        @Override
                        public IModelState getState() {
                            return MachineProperties.getRotation(side);
                        }
                    }, DefaultVertexFormats.BLOCK);
                    voltageModels.put(voltage, model);
                }
                sideModels.put(type, voltageModels);
            }
            sides.put(side, sideModels);

            Map<Machine, Map<Voltage, IBakedModel>> machinesOff = new HashMap<>();
            Map<Machine, Map<Voltage, IBakedModel>> machinesOn = new HashMap<>();
            for (Machine machine : machineRegistry.getMachines()) {
                Map<Voltage, IBakedModel> voltageOff = new HashMap<>();
                Map<Voltage, IBakedModel> voltageOn = new HashMap<>();
                for (Voltage voltage : Voltage.getAll()) {
                    IUnbakedModel unbakedOff = loader.getUnbakedModel(textureLoader.getMachineCasingModel(machine, voltage, false));
                    IUnbakedModel unbakedOn = loader.getUnbakedModel(textureLoader.getMachineCasingModel(machine, voltage, true));
                    unbakedOff.getTextures(loader::getUnbakedModel, errors);
                    unbakedOn.getTextures(loader::getUnbakedModel, errors);
                    voltageOff.put(voltage, unbakedOff.bake(loader, ModelLoader.defaultTextureGetter(), new ISprite() {
                        @Override
                        public IModelState getState() {
                            return MachineProperties.getRotation(side);
                        }
                    }, DefaultVertexFormats.BLOCK));
                    voltageOn.put(voltage, unbakedOn.bake(loader, ModelLoader.defaultTextureGetter(), new ISprite() {
                        @Override
                        public IModelState getState() {
                            return MachineProperties.getRotation(side);
                        }
                    }, DefaultVertexFormats.BLOCK));
                }
                machinesOff.put(machine, voltageOff);
                machinesOn.put(machine, voltageOn);
            }
            frontOff.put(side, machinesOff);
            frontOn.put(side, machinesOn);
        }
        for (String error : errors) {
            System.out.println("DS MODEL TEX ERROR: " + error);
        }
        baked = true;
    }
}
