package co.q64.dynamicalsystems.client.texture;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MachineTextureMap {
    protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;
    protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    protected @Inject IdentifierUtil identifiers;

    protected @Inject MachineTextureMap() {}

    public Map<String, ResourceLocation> getTextures(Machine machine, Voltage voltage, boolean on) {
        Map<String, ResourceLocation> result = new HashMap<>();
        ResourceLocation base = identifiers.get(machine.getBaseTextureOverride().apply(voltage).orElse("block/" + machine.getBaseTexture() + "_tier_" + voltage.tierTextureName()));
        result.put("base", base);
        /*
        if (on) {
            result.put("overlay", identifiers.get("block/machine/" + machine.getOverlayOnTextureOverride().apply(voltage).orElse(machine.getOverlayOnTexture())));
        } else {
            result.put("overlay", identifiers.get("block/machine/" + machine.getOverlayOffTextureOverride().apply(voltage).orElse(machine.getOverlayOffTexture())));
        }
        */
        result.put("overlay", getMachineOverlayTexture(machine, voltage, MachineSideConfiguration.FRONT, on));
        ResourceLocation overlay = result.get("overlay");
        ResourceLocation generated = getMachineCasingTexture(machine, voltage, MachineSideConfiguration.FRONT, on); //identifiers.get("machine_front_" + machine.getId() + "_" + voltage.tierTextureName() + "_" + on + "_generated");
        alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(generated, base, overlay));
        result.put("base", generated);
        return result;
    }

    public Map<String, ResourceLocation> getSideTextures(Voltage voltage, MachineSideConfiguration type) {
        Map<String, ResourceLocation> result = new HashMap<>();
        ResourceLocation base = identifiers.get("block/machine_casing_tier_" + voltage.tierTextureName());
        ResourceLocation overlay = identifiers.get(type.getTextureName());
        ResourceLocation generated = getMachineCasingTexture(null, voltage, type, false); //identifiers.get("machine_side_" + type.getName() + "_" + voltage.tierTextureName() + "_generated");
        alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(generated, base, overlay));
        result.put("base", generated);
        result.put("overlay", overlay);
        return result;
    }

    public ResourceLocation getMachineCasingTexture(Machine machine, Voltage voltage, MachineSideConfiguration type, boolean on) {
        if (type == MachineSideConfiguration.FRONT) {
            return identifiers.get("machine_front_" + machine.getId() + "_" + voltage.tierTextureName() + "_" + on + "_generated");
        }
        return identifiers.get("machine_side_" + type.getName() + "_" + voltage.tierTextureName() + "_generated");
    }

    public ResourceLocation getMachineOverlayTexture(Machine machine, Voltage voltage, MachineSideConfiguration type, boolean on) {
        if (type == MachineSideConfiguration.FRONT) {
            if (on) {
                return identifiers.get("block/machine/" + machine.getOverlayOnTextureOverride().apply(voltage).orElse(machine.getOverlayOnTexture()));
            } else {
                return identifiers.get("block/machine/" + machine.getOverlayOffTextureOverride().apply(voltage).orElse(machine.getOverlayOffTexture()));
            }
        }
        return identifiers.get(type.getTextureName());
    }

    public ResourceLocation getMachineCasingModel(Voltage voltage, MachineSideConfiguration type) {
        return identifiers.get(voltage.tierTextureName() + "_" + type.getName() + "_generated");
    }

    public ResourceLocation getMachineCasingModel(Machine machine, Voltage voltage, boolean off) {
        return identifiers.get(machine.getId() + "_" + voltage.tierTextureName() + "_front_" + off + "_generated");
    }

    public ResourceLocation getTexturePath(ResourceLocation id) {
        return new ResourceLocation(id.getNamespace(), "textures/" + id.getPath() + ".png");
    }
}
