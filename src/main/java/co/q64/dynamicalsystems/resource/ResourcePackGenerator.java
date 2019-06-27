package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Singleton
public class ResourcePackGenerator {
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifierUtil;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private @Getter int generatedJSONs, generatedBlockstates, generatedItemModels, generatedBlockModels, generatedLootTables;
    private @Getter Map<ResourceLocation, byte[]> virtualResourcePack = new HashMap<>();

    protected @Inject ResourcePackGenerator() {}

    public void writeItemModel(String name, ResourceLocation parent) {
        writeItemModel(name, parent, Collections.emptyList());
    }

    public void writeItemModel(String name, List<ResourceLocation> textures) {
        writeItemModel(name, new ResourceLocation("item/generated"), textures);
    }

    public void writeItemModel(String name, ResourceLocation parent, List<ResourceLocation> textures) {
        writeItemModelInternal(name, parent, textures);
    }

    public void writeBlockstate(String name) {
        writeSimpleBlockstateInternal(name);
    }

    public void writeBlockstate(String name, MultipartBuilder builder) {
        WriteMultipartBlockStateInternal(name, builder);
    }

    public void writeBlockModel(String name, ResourceLocation parent, Map<String, ResourceLocation> textures) {
        writeBlockModelInternal(name, parent, textures);
    }

    public void writeTranslations(Map<String, String> translations) {
        writeTranslationsInternal(translations);
    }

    public void writeLootTable(String name, ResourceLocation drop) {
        writeLootTableInternal(name, drop);
    }

    private void writeItemModelInternal(String name, ResourceLocation parent, List<ResourceLocation> layers) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", parent.toString());
        JsonObject textures = new JsonObject();
        for (int layer = 0; layer < layers.size(); layer++) {
            textures.addProperty("layer" + layer, layers.get(layer).toString());
        }
        model.add("textures", textures);
        writeJson(model, "models/item/" + name + ".json");
        generatedItemModels++;
    }

    private void writeSimpleBlockstateInternal(String name) {
        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject variant = new JsonObject();
        variant.addProperty("model", identifierUtil.get("block/" + name).toString());
        variants.add("", variant);
        blockstate.add("variants", variants);
        writeJson(blockstate, "blockstates/" + name + ".json");
        generatedBlockstates++;
    }

    private void WriteMultipartBlockStateInternal(String name, MultipartBuilder builder) {
        writeJson(builder.build(), "blockstates/" + name + ".json");
        generatedBlockstates++;
    }

    private void writeBlockModelInternal(String name, ResourceLocation parent, Map<String, ResourceLocation> texmap) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", parent.toString());
        JsonObject textures = new JsonObject();
        for (Entry<String, ResourceLocation> entry : texmap.entrySet()) {
            textures.addProperty(entry.getKey(), entry.getValue().toString());
        }
        model.add("textures", textures);
        writeJson(model, "models/block/" + name + ".json");
        generatedBlockModels++;
    }

    private void writeLootTableInternal(String name, ResourceLocation drop) {
        JsonObject result = new JsonObject();
        result.addProperty("type", new ResourceLocation("block").toString());
        JsonArray pools = new JsonArray();
        JsonObject pool = new JsonObject();
        pool.addProperty("name", name);
        pool.addProperty("rolls", 1);
        JsonArray entries = new JsonArray();
        JsonObject entry = new JsonObject();
        entry.addProperty("type", new ResourceLocation("item").toString());
        entry.addProperty("name", identifierUtil.get(name).toString());
        entries.add(entry);
        pool.add("entries", entries);
        JsonArray conditions = new JsonArray();
        JsonObject condition = new JsonObject();
        condition.addProperty("condition", new ResourceLocation("survives_explosion").toString());
        conditions.add(condition);
        pool.add("conditions", conditions);
        pools.add(pool);
        result.add("pools", pools);
        writeJson(result, "loot_tables/extra/" + name + ".json");
        generatedLootTables++;
    }

    private void writeTranslationsInternal(Map<String, String> translations) {
        JsonObject result = new JsonObject();
        for (Entry<String, String> entry : translations.entrySet()) {
            result.addProperty(entry.getKey(), entry.getValue());
        }
        writeJson(result, "lang/en_us.json");
    }

    private void writeJson(JsonObject object, String path) {
        writeJson(object, identifierUtil.get(path));
    }

    private void writeJson(JsonObject object, ResourceLocation path) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(result))) {
                gson.toJson(object, writer);
            }
            result.flush();
            result.close();
            virtualResourcePack.put(path, result.toByteArray());
            generatedJSONs++;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to write generated resources");
        }
    }

    @Inject
    protected void initialize() {
        JsonObject packMeta = new JsonObject();
        JsonObject pack = new JsonObject();
        pack.addProperty("pack_format", 4);
        pack.addProperty("description", "Mojang wants JSON files? Mojang gets JSON files...");
        packMeta.add("pack", pack);
        writeJson(packMeta, "pack.mcmeta");
    }
}
