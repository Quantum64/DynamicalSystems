package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.client.model.CustomModel;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestFactory;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Singleton
public class ModelLoader implements ICustomModelLoader {
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject ItemUtil itemUtil;
    protected @Inject MaterialTextureMap materialTextureMap;
    protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;
    protected @Inject Set<CustomModel> customUnbakedModels;

    protected @Inject ModelLoader() {}

    @Override
    public boolean accepts(ResourceLocation resourceId) {
        if (!resourceId.getNamespace().equals(modId)) {
            return false;
        }
        String[] parts = resourceId.getPath().split("/");
        String registry = parts[0];
        String resource = parts[1];
        for (int i = 2; i < parts.length; i++) {
            resource += "/" + parts[i];
        }
        ResourceLocation identifier = identifierUtil.get(resource);
        if (itemUtil.getMaterialItem(identifier).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public IUnbakedModel loadModel(ResourceLocation resourceId) throws Exception {
        if (resourceId.getNamespace().equals(modId)) {
            String[] parts = resourceId.getPath().split("/");
            String registry = parts[0];
            String resource = parts[1];
            for (int i = 2; i < parts.length; i++) {
                resource += "/" + parts[i];
            }
            ResourceLocation identifier = identifierUtil.get(resource);
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
                        return new BlockModel(new ModelResourceLocation(identifierUtil.get(resource), ""), Collections.emptyList(), Collections.emptyMap(), false, true, ItemCameraTransforms.DEFAULT, Collections.emptyList());
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
                    ResourceLocation identifier = identifierUtil.get(modelId.getPath());
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
                            alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifierUtil.get(generated), new ResourceLocation(((ComponentOre) component).getBaseTexture()), identifierUtil.get(textureList.get(0))));
                        }
                        if (textureList.size() > 1) {
                            String generated = "generated_block_" + component.getTextureName();
                            textures.put("all", modId + ":" + generated);
                            textures.put("overlay", modId + ":" + textureList.get(1));
                            alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifierUtil.get(generated), identifierUtil.get(textureList.get(0)), identifierUtil.get(textureList.get(1))));
                            return new BlockModel(identifierUtil.get(modelOverload.isEmpty() ? "block/block_material_overlay" : modelOverload), Collections.emptyList(), textures, false, false, ItemCameraTransforms.DEFAULT, Collections.emptyList());
                        }
                        System.out.println("Loaded variant " + resourceId);
                        return new BlockModel(identifierUtil.get(modelOverload.isEmpty() ? "block/block_material" : modelOverload), Collections.emptyList(), textures, false, false, ItemCameraTransforms.DEFAULT, Collections.emptyList());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }
}
