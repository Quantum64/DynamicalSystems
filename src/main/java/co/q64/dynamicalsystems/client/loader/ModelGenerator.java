package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.client.model.CustomModel;
import co.q64.dynamicalsystems.client.texture.MachineTextureMap;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.resource.MultipartBuilderFactory;
import co.q64.dynamicalsystems.resource.ResourcePackGenerator;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.ItemUtil;
import co.q64.dynamicalsystems.util.Logger;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ModelGenerator {
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifiers;
    protected @Inject ItemUtil itemUtil;
    protected @Inject MaterialTextureMap materialTextureMap;
    protected @Inject MachineTextureMap machineTextureMap;
    protected @Inject MultipartBuilderFactory multipartFactory;
    //protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    //protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;
    protected @Inject Set<CustomModel> customUnbakedModels;
    protected @Inject ResourcePackGenerator generator;
    protected @Inject Logger logger;

    protected @Inject ModelGenerator() {}

    public void generateModels() {
        logger.info("Generating models and blockstates...");
        long start = System.currentTimeMillis();
        int generatedItems = 0, generatedBlockstates = 0, generatedBlocks = 0;
        for (MaterialItem item : itemUtil.getMaterialItems()) {
            if (item.isBlock()) {
                Component component = item.getComponent();
                generator.writeItemModel(item.getId(), identifiers.get("block/" + item.getId()));
                generator.writeBlockstate(item.getId());
                generatedBlockstates++;
                String parent = component.getModel();
                List<String> textureList = materialTextureMap.getTextures(item);
                Map<String, ResourceLocation> textures = new HashMap<>();
                textures.put("all", identifiers.get(textureList.get(0)));
                if (component instanceof ComponentOre) {
                    //String generated = "generated_block_ore_" + ((ComponentOre) component).getBaseTexture().replace("/", "_");
                    textures.put("base", new ResourceLocation(((ComponentOre) component).getBaseTexture()));
                    //alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifiers.get(generated), new ResourceLocation(((ComponentOre) component).getBaseTexture()), identifiers.get(textureList.get(0))));
                } else if (textureList.size() > 1) {
                    //String generated = "generated_block_" + component.getTextureName();
                    textures.put("all", identifiers.get(component.getTextureName()));
                    textures.put("overlay", identifiers.get(textureList.get(1)));
                    //alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifiers.get(generated), identifiers.get(textureList.get(0)), identifiers.get(textureList.get(1))));
                    if (parent.isEmpty()) {
                        parent = "block/block_material_overlay";
                    }
                }
                if (parent.isEmpty()) {
                    parent = "block/block_material";
                }
                generator.writeBlockModel(item.getId(), identifiers.get(parent), textures);
                generatedBlocks++;
            } else {
                generator.writeItemModel(item.getId(), materialTextureMap.getTextures(item).stream().map(s -> identifiers.get(s)).collect(Collectors.toList()));
            }
            generatedItems++;
        }
        for (MachineBlockItem machine : itemUtil.getMachineItems()) {
            generator.writeItemModel(machine.getId(), identifiers.get("block/" + machine.getId() + "_off"));
            generator.writeBlockstate(machine.getId(), multipartFactory.create()
                    .when("running", "false").apply(machine.getId() + "_off")
                    .when("running", "true").apply(machine.getId() + "_on"));
                    // TODO Side configurations
            generator.writeBlockModel(machine.getId() + "_off", identifiers.get("block/block_machine"), machineTextureMap.getTextures(machine.getBlock(), false));
            generator.writeBlockModel(machine.getId() + "_on", identifiers.get("block/block_machine"), machineTextureMap.getTextures(machine.getBlock(), true));
            generatedBlocks++;
            generatedBlockstates++;
            generatedItems++;
        }
        logger.info("Generated " + generatedItems + " item models, " + generatedBlockstates + " blockstates, and " +
                generatedBlocks + " block models for a total of " + (generatedItems + generatedBlockstates + generatedBlocks) +
                " JSON files (" + (System.currentTimeMillis() - start) + " ms)");
    }
}
