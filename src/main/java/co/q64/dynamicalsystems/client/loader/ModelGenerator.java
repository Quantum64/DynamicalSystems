package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.client.model.CustomModel;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
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
                }
                if (textureList.size() > 1) {
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
        logger.info("Generated " + generatedItems + " item models, " + generatedBlockstates + " blockstates, and " +
                generatedBlocks + " block models for a total of " + (generatedItems + generatedBlockstates + generatedBlocks) +
                " JSON files (" + (System.currentTimeMillis() - start) + " ms)");
    }
    /*
    public IUnbakedModel loadModel(ResourceLocation resourceId) throws Exception {
        if (resourceId.getNamespace().equals(modId)) {
            String[] parts = resourceId.getPath().split("/");
            String registry = parts[0];
            String resource = parts[1];
            for (int i = 2; i < parts.length; i++) {
                resource += "/" + parts[i];
            }
            ResourceLocation identifier = identifiers.get(resource);
            if (itemUtil.getMaterialItem(identifier).isPresent()) {
                MaterialItem materialItem = itemUtil.getMaterialItem(identifier).get();
                if (registry.equals("item")) {
                    if (materialItem.isBlock()) {
                        for (CustomModel customUnbakedModel : customUnbakedModels) {
                            if (customUnbakedModel.getId().equals(materialItem.getComponent().getModel())) {
                                return customUnbakedModel;
                            }
                        }
                        System.out.println("Loaded block model " + resourceId);
                        return new BlockModel(new ModelResourceLocation(identifiers.get(resource), ""), Collections.emptyList(), Collections.emptyMap(), false, true, ItemCameraTransforms.DEFAULT, Collections.emptyList());
                    } else {
                        List<String> layers = materialTextureMap.getTextures(materialItem);
                        Map<String, String> textures = new HashMap<>();
                        for (int index = 0; index < layers.size(); index++) {
                            textures.put("layer" + index, modId + ":" + layers.get(index));
                        }
                        System.out.println("Loaded item model " + resourceId);
                        return new BlockModel(new ResourceLocation("item/generated"), Collections.emptyList(), textures, false, false, ItemCameraTransforms.DEFAULT, Collections.emptyList());
                    }
                }
            }
        }

        if (resourceId instanceof ModelResourceLocation) {
            try {
                ModelResourceLocation modelId = (ModelResourceLocation) resourceId;
                if (modelId.getNamespace().equals(modId) && modelId.getVariant().equals("")) {
                    ResourceLocation identifier = identifiers.get(modelId.getPath());
                    if (itemUtil.getMaterialItem(identifier).isPresent()) {
                        MaterialItem materialItem = itemUtil.getMaterialItem(identifier).get();
                        Map<String, String> textures = new HashMap<>();
                        List<String> textureList = materialTextureMap.getTextures(materialItem);
                        textures.put("all", modId + ":" + textureList.get(0));
                        Component component = materialItem.getComponent();
                        String modelOverload = component.getModel();
                        for (CustomModel customUnbakedModel : customUnbakedModels) {
                            if (customUnbakedModel.getId().equals(modelOverload)) {
                                return customUnbakedModel;
                            }
                        }
                        if (component instanceof ComponentOre) {
                            String generated = "generated_block_ore_" + ((ComponentOre) component).getBaseTexture().replace("/", "_");
                            textures.put("base", modId + ":" + generated);
                            alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifiers.get(generated), new ResourceLocation(((ComponentOre) component).getBaseTexture()), identifiers.get(textureList.get(0))));
                        }
                        if (textureList.size() > 1) {
                            String generated = "generated_block_" + component.getTextureName();
                            textures.put("all", modId + ":" + generated);
                            textures.put("overlay", modId + ":" + textureList.get(1));
                            alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifiers.get(generated), identifiers.get(textureList.get(0)), identifiers.get(textureList.get(1))));
                            return new BlockModel(identifiers.get(modelOverload.isEmpty() ? "block/block_material_overlay" : modelOverload), Collections.emptyList(), textures, false, false, ItemCameraTransforms.DEFAULT, Collections.emptyList());
                        }
                        System.out.println("Loaded variant " + resourceId);
                        return new BlockModel(identifiers.get(modelOverload.isEmpty() ? "block/block_material" : modelOverload), Collections.emptyList(), textures, false, false, ItemCameraTransforms.DEFAULT, Collections.emptyList());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

     */
}
