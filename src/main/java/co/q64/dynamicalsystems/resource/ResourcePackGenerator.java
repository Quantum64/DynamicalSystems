package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        writeSimpleBlockstateInternal(name, builder);
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
        writeJson(model, "models/item/" + name + ".json");
    }

    private void writeSimpleBlockstateInternal(String name) {
        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject variant = new JsonObject();
        variant.addProperty("model", identifierUtil.get("block/" + name).toString());
        variants.add("", variant);
        blockstate.add("variants", variants);
        writeJson(blockstate, "blockstates/" + name + ".json");
    }

    private void WriteMultipartBlockStateInternal(String name, MultipartBuilder builder) {
        writeJson(builder.build(), "blockstates/" + name + ".json");
    }

    private void writeBlockModelInternal(String name, ResourceLocation parent, Map<String, ResourceLocation> texmap) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", parent.toString());
        JsonObject textures = new JsonObject();
        for (Entry<String, ResourceLocation> entry : texmap.entrySet()) {
            textures.addProperty(entry.getKey(), entry.getValue().toString());
        }
        model.add("textures", textures);
        writeJson(model, "models/" + "block/" + name + ".json");
    }

    private void writeJson(JsonObject object, String path) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(result))) {
                gson.toJson(object, writer);
            }
            result.flush();
            result.close();
            virtualResourcePack.put(identifierUtil.get(path), result.toByteArray());
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
