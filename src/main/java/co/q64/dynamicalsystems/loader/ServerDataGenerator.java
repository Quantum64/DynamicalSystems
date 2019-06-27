package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.block.item.BaseBlockItem;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.IdentifiableItem;
import co.q64.dynamicalsystems.recipe.Recipe;
import co.q64.dynamicalsystems.recipe.RecipeType;
import co.q64.dynamicalsystems.recipe.Recipes;
import co.q64.dynamicalsystems.resource.ResourcePackGenerator;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.Logger;
import co.q64.dynamicalsystems.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class ServerDataGenerator {
    protected @Inject ResourcePackGenerator packGenerator;
    protected @Inject Recipes recipes;
    protected @Inject RegistryUtil registryUtil;
    protected @Inject IdentifierUtil identifiers;
    protected @Inject Logger logger;

    @Inject
    protected ServerDataGenerator() {}

    public void generateData() {
        long start = System.currentTimeMillis();
        for (Block block : registryUtil.getBlocks()) {
            packGenerator.writeLootTable(block.getRegistryName().getPath(), block.getRegistryName());
        }
        for (BaseBlockItem blockItem : registryUtil.getBlockItems()) {
            writeTag(blockItem);
        }
        for (BaseItem item : registryUtil.getBaseItems()) {
            writeTag(item);
        }
        for (Recipe recipe : recipes.get(RecipeType.CRAFTING, RecipeType.SMELTING)) {
            packGenerator.writeRecipe(recipe);
        }
        logger.info("Generated " + packGenerator.getGeneratedJSONs() + " server data JSON files (" + (System.currentTimeMillis() - start) + " ms)");
    }

    private void writeTag(IdentifiableItem item) {
        List<ResourceLocation> parents = new ArrayList<>(1);
        List<ResourceLocation> items = Arrays.asList(identifiers.get(item.getId()));
        if (item.getTag().isPresent()) {
            ResourceLocation forgeTag = new ResourceLocation("forge", item.getTag().get());
            packGenerator.writeItemTag(forgeTag, items);
            parents.add(forgeTag);
        }
        packGenerator.writeItemTag(item.getId(), parents, items);
    }
}
