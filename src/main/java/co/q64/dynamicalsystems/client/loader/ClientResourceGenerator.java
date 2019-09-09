package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.qualifier.ConstantQualifiers.ModId;
import co.q64.dynamicalsystems.block.item.ExtraBlockItem;
import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestFactory;
import co.q64.dynamicalsystems.client.texture.AlphaMapRequestRegistry;
import co.q64.dynamicalsystems.client.texture.AlphaMapSpriteFactory;
import co.q64.dynamicalsystems.client.texture.MachineTextureMap;
import co.q64.dynamicalsystems.client.texture.MaterialTextureMap;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineRegistry;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.material.components.CableComponent;
import co.q64.dynamicalsystems.resource.MultipartBuilder;
import co.q64.dynamicalsystems.resource.MultipartBuilderFactory;
import co.q64.dynamicalsystems.resource.ResourcePackGenerator;
import co.q64.dynamicalsystems.resource.TranslationService;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.ItemUtil;
import co.q64.dynamicalsystems.util.Logger;
import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class ClientResourceGenerator {
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifiers;
    protected @Inject ItemUtil itemUtil;
    protected @Inject MaterialTextureMap materialTextureMap;
    protected @Inject MachineTextureMap machineTextureMap;
    protected @Inject MultipartBuilderFactory multipartFactory;
    protected @Inject ResourcePackGenerator generator;
    protected @Inject TranslationService translationService;
    protected @Inject TextureLoader textureLoader;
    protected @Inject MachineRegistry machineRegistry;
    protected @Inject Logger logger;
    protected @Inject RegistryUtil registryUtil;

    protected @Inject AlphaMapSpriteFactory alphaMapSpriteFactory;
    protected @Inject AlphaMapRequestRegistry alphaMapRequestRegistry;
    protected @Inject AlphaMapRequestFactory alphaMapRequestFactory;

    protected @Inject ClientResourceGenerator() {}

    public void generateModels() {
        long start = System.currentTimeMillis();
        for (MaterialItem item : itemUtil.getMaterialItems()) {
            if (item.isBlock()) {
                Component component = item.getComponent();
                generator.writeItemModel(item.getId(), identifiers.get("block/" + item.getId()));
                if (component instanceof CableComponent) {
                    generator.writeBlockstate(item.getId(), multipartFactory.create()
                            .apply(identifiers.get("block/cable_core"))
                    );
                    continue;
                }
                generator.writeBlockstate(item.getId());
                String parent = component.getModel();
                List<String> textureList = materialTextureMap.getTextures(item);
                Map<String, ResourceLocation> textures = new HashMap<>();
                textures.put("all", identifiers.get(textureList.get(0)));
                if (component instanceof ComponentOre) {
                    String generated = "generated_block_ore_" + ((ComponentOre) component).getBaseTexture().replace("/", "_");
                    textures.put("base", identifiers.get(generated));
                    alphaMapRequestRegistry.requestAlphaMap(alphaMapRequestFactory.create(identifiers.get(generated), new ResourceLocation(((ComponentOre) component).getBaseTexture()), identifiers.get(textureList.get(0))));
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
            } else {
                generator.writeItemModel(item.getId(), materialTextureMap.getTextures(item).stream().map(s -> identifiers.get(s)).collect(Collectors.toList()));
            }
        }
        for (MachineSideConfiguration type : MachineSideConfiguration.getValues()) {
            if (type == MachineSideConfiguration.FRONT) {
                continue;
            }
            registryUtil.registerTexture(identifiers.get(type.getTextureName()));
            for (Voltage voltage : Voltage.getAll()) {
                generator.writeModel(machineTextureMap.getMachineCasingModel(voltage, type).getPath(), identifiers.get("block/block_machine"), machineTextureMap.getSideTextures(voltage, type));
            }
        }
        for (MachineBlockItem machine : itemUtil.getMachineItems()) {
            generator.writeItemModel(machine.getId(), identifiers.get("block/" + machine.getId()));

            generator.writeBlockstate(machine.getId(), multipartFactory.create()
                            // Currently needed to coerce texture loading of the casing and overlays
                            // https://github.com/MinecraftForge/MinecraftForge/pull/5870
                            .apply(identifiers.get(machine.getOffModel()))
                            .apply(identifiers.get(machine.getOnModel()))

                    // Probably not needed
                    //.apply(identifiers.get("block/dynamic/machine"))
            );
/*
            // https://github.com/MinecraftForge/MinecraftForge/pull/5870
            for (Voltage voltage : Voltage.getAll()) {
                generator.writeBlockModel(machine.getId() + "_off", identifiers.get("block/block_machine"), machineTextureMap.getTextures(machine.getBlock(), false));
                generator.writeBlockModel(machine.getId() + "_on", identifiers.get("block/block_machine"), machineTextureMap.getTextures(machine.getBlock(), true));
            }
            */
        }
        for (Machine machine : machineRegistry.getMachines()) {
            for (Voltage voltage : Voltage.getAll()) {
                Map<String, ResourceLocation> offTex = machineTextureMap.getTextures(machine, voltage, false);
                Map<String, ResourceLocation> onTex = machineTextureMap.getTextures(machine, voltage, true);
                generator.writeModel(machineTextureMap.getMachineCasingModel(machine, voltage, false).getPath(), identifiers.get("block/block_machine"), offTex);
                generator.writeModel(machineTextureMap.getMachineCasingModel(machine, voltage, true).getPath(), identifiers.get("block/block_machine"), onTex);
                offTex.values().forEach(registryUtil::registerTexture);
                onTex.values().forEach(registryUtil::registerTexture);
            }
        }
        //generator.writeBlockModel("dynamic/machine", identifiers.get("block/placeholder"), Collections.singletonMap("all", new ResourceLocation("block/stone"))); // Probably not needed
        for (ExtraBlockItem extraBlockItem : itemUtil.getExtraBlockItems()) {
            Optional<MultipartBuilder> stateBuilder = extraBlockItem.getDefinition().getStateBuilder();
            if (stateBuilder.isPresent()) {
                generator.writeBlockstate(extraBlockItem.getId(), stateBuilder.get());
            } else {
                generator.writeBlockstate(extraBlockItem.getId());
            }
        }
        logger.info("Generated " + generator.getGeneratedItemModels() + " item models, " + generator.getGeneratedBlockstates() + " blockstates, and " +
                generator.getGeneratedBlockModels() + " block models for a total of " + (generator.getGeneratedItemModels() + generator.getGeneratedBlockstates() + generator.getGeneratedBlockModels()) +
                " JSON files (" + (System.currentTimeMillis() - start) + " ms)");
        start = System.currentTimeMillis();
        generator.writeTranslations(translationService.getTranslations());
        logger.info("Generated " + translationService.getTranslations().size() + " default translations (" + (System.currentTimeMillis() - start) + " ms)");

        logger.info("Total generated JSON files: " + generator.getGeneratedJSONs());
    }
}
