package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.binders.ConstantBinders.ConfigFolder;
import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Singleton
public class ResourcePackGenerator {
    private static final String s = File.separator;
    private static final boolean REWRITE_DEBUG = true;

    protected @Inject @ConfigFolder File configFolder;
    protected @Inject @ModId String modId;
    protected @Inject IdentifierUtil identifierUtil;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File resourcePackFolder;

    protected @Inject ResourcePackGenerator() {}

    public File getResourcePackFolder() {
        return resourcePackFolder;
    }

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
        writeBlockstateInternal(name);
    }

    public void writeBlockModel(String name, ResourceLocation parent, Map<String, ResourceLocation> textures) {
        writeBlockModelInternal(name, parent, textures);
    }

    private void writeItemModelInternal(String name, ResourceLocation parent, List<ResourceLocation> layers) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", parent.toString());
        JsonObject textures = new JsonObject();
        for (int layer = 0; layer < layers.size(); layer++) {
            textures.addProperty("layer" + layer, layers.get(layer).toString());
        }
        model.add("textures", textures);
        writeAssetJson(model, "models" + s + "item" + s + name + ".json");
    }

    private void writeBlockstateInternal(String name) {
        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject variant = new JsonObject();
        variant.addProperty("model", identifierUtil.get("block/" + name).toString());
        variants.add("", variant);
        blockstate.add("variants", variants);
        writeAssetJson(blockstate, "blockstates" + s + name + ".json");
    }

    private void writeBlockModelInternal(String name, ResourceLocation parent, Map<String, ResourceLocation> texmap) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", parent.toString());
        JsonObject textures = new JsonObject();
        for (Entry<String, ResourceLocation> entry : texmap.entrySet()) {
            textures.addProperty(entry.getKey(), entry.getValue().toString());
        }
        model.add("textures", textures);
        writeAssetJson(model, "models" + s + "block" + s + name + ".json");
    }

    private void writeAssetJson(JsonObject object, String path) {
        writeJson(object, "assets" + s + modId + s + path);
    }

    private void writeJson(JsonObject object, String path) {
        try {
            File file = new File(resourcePackFolder, path);
            if (file.exists()) {
                return;
            }
            file.getParentFile().mkdirs();
            try (Writer writer = new FileWriter(file, false)) {
                gson.toJson(object, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to write generated resources");
        }
    }

    @Inject
    protected void initialize() {
        resourcePackFolder = new File(configFolder, "generated_resources");
        if (REWRITE_DEBUG) {
            try {
                Files.walk(resourcePackFolder.toPath())
                        .sorted(Comparator.reverseOrder())
                        .forEach(t -> {
                            try {
                                Files.delete(t);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resourcePackFolder.mkdirs();
        JsonObject packMeta = new JsonObject();
        JsonObject pack = new JsonObject();
        pack.addProperty("pack_format", 4);
        pack.addProperty("description", "Mojang wants JSON files? Mojang gets JSON files...");
        packMeta.add("pack", pack);
        writeJson(packMeta, "pack.mcmeta");
    }
}
