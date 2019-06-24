package co.q64.dynamicalsystems.client.texture;

import co.q64.dynamicalsystems.block.MachineBlock;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MachineTextureMap {
    protected @Inject IdentifierUtil identifiers;

    protected @Inject MachineTextureMap() {}

    public Map<String, ResourceLocation> getTextures(MachineBlock block, boolean on) {
        Map<String, ResourceLocation> result = new HashMap<>();
        Machine machine = block.getMachine();
        Voltage voltage = block.getVoltage();
        result.put("base", identifiers.get(machine.getBaseTextureOverride().apply(voltage).orElse("block/" + machine.getBaseTexture() + "_tier_" + voltage.tierTextureName())));
        if (on) {
            result.put("overlay", identifiers.get("block/machine/" + machine.getOverlayOnTextureOverride().apply(voltage).orElse(machine.getOverlayOnTexture())));
        } else {
            result.put("overlay", identifiers.get("block/machine/" + machine.getOverlayOffTextureOverride().apply(voltage).orElse(machine.getOverlayOffTexture())));
        }
        return result;
    }
}
