package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.client.model.CustomModel;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestFactory;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.util.ItemUtil;
import co.q64.dynamicalsystems.util.identifier.IdentifierUtil;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Singleton
public class ModelLoader {
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject ItemUtil itemUtil;
    protected @Inject MaterialTextureMap materialTextureMap;
    protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;
    protected @Inject ModelLoadingRegistry modelLoadingRegistry;
    protected @Inject Set<CustomModel> customUnbakedModels;

    protected @Inject ModelLoader() {}

    public void loadModels() {
        modelLoadingRegistry.registerResourceProvider(manager -> (resourceId, context) -> {
            if (resourceId.getNamespace().equals(modId)) {
                String[] parts = resourceId.getPath().split("/");
                String registry = parts[0];
                String resource = parts[1];
                for (int i = 2; i < parts.length; i++) {
                    resource += "/" + parts[i];
                }
                Identifier identifier = identifierUtil.get(resource);
                if (itemUtil.getMaterialItem(identifier).isPresent()) {
                    MaterialItem materialItem = itemUtil.getMaterialItem(identifier).get();
                    if (registry.equals("item")) {
                        if (materialItem.isBlock()) {
                            for (CustomModel customUnbakedModel : customUnbakedModels) {
                                if (customUnbakedModel.getId().equals(materialItem.getComponent().getModel())) {
                                    return customUnbakedModel;
                                }
                            }
                            return new JsonUnbakedModel(new ModelIdentifier(identifierUtil.get(resource), ""), Collections.emptyList(), Collections.emptyMap(), false, true, ModelTransformation.NONE, Collections.emptyList());
                        } else {
                            List<String> layers = materialTextureMap.getTextures(materialItem);
                            Map<String, String> textures = new HashMap<>();
                            for (int index = 0; index < layers.size(); index++) {
                                textures.put("layer" + index, modId + ":" + layers.get(index));
                            }
                            return new JsonUnbakedModel(new Identifier("item/generated"), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
                        }

                    }
                }
            }
            return null;
        });

        modelLoadingRegistry.registerVariantProvider(manager -> (resourceId, context) -> {
            try {
                ModelIdentifier modelId = (ModelIdentifier) resourceId;
                if (modelId.getNamespace().equals(modId) && modelId.getVariant().equals("")) {
                    Identifier identifier = identifierUtil.get(modelId.getPath());
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
                            alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(generated, ((ComponentOre) component).getBaseTexture(), textureList.get(0)));
                        }
                        if (textureList.size() > 1) {
                            String generated = "generated_block_" + component.getTextureName();
                            textures.put("all", modId + ":" + generated);
                            textures.put("overlay", modId + ":" + textureList.get(1));
                            alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(generated, textureList.get(0), textureList.get(1)));
                            return new JsonUnbakedModel(identifierUtil.get(modelOverload.isEmpty() ? "block/block_material_overlay" : modelOverload), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
                        }
                        return new JsonUnbakedModel(identifierUtil.get(modelOverload.isEmpty() ? "block/block_material" : modelOverload), Collections.emptyList(), textures, false, false, ModelTransformation.NONE, Collections.emptyList());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
