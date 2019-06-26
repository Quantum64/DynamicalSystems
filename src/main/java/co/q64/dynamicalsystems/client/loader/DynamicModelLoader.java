package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.client.model.MachineModel;
import co.q64.dynamicalsystems.client.model.MachineModelFactory;
import co.q64.dynamicalsystems.client.model.MachineSideModels;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class DynamicModelLoader {
    protected @Inject MachineModelFactory machineModelFactory;
    protected @Inject IdentifierUtil identifiers;
    protected @Inject MachineSideModels machineSideOverlays;
    protected @Inject ItemUtil itemUtil;

    protected @Inject DynamicModelLoader() {}

    public void loadModels(ModelBakeEvent event) {
        // Machines
        machineSideOverlays.bake(event.getModelLoader());
        Map<String, MachineModel> machineModelOverloads = new HashMap<>();
        for (MachineBlockItem machine : itemUtil.getMachineItems()) {
            MachineModel model = machineModelFactory.create(machine);
            model.bake(event.getModelLoader());
            machineModelOverloads.put(machine.getId(), model);
        }
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for (ResourceLocation location : registry.keySet()) {
            ModelResourceLocation modelResourceLocation = new ModelResourceLocation(location.toString());
            if (modelResourceLocation.getVariant().equals("inventory")) {
                // May add item rendering later but for now we have to load the base models as JSONs anyway
                // since there's no way to get textures to load without that
                continue;
            }
            ResourceLocation block = new ResourceLocation(modelResourceLocation.getNamespace(), modelResourceLocation.getPath());
            if (machineModelOverloads.keySet().contains(block.getPath())) {
                System.out.println("MODEL CONTAINS MACHINE: " + location.toString());
                registry.put(location, machineModelOverloads.get(block.getPath()));
            }
        }

    }
}
