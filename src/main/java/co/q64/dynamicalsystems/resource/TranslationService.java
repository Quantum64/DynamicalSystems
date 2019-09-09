package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.qualifier.ConstantQualifiers.ModId;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentBlock;
import co.q64.dynamicalsystems.material.base.Material;
import lombok.Getter;
import net.minecraft.util.text.TranslationTextComponent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class TranslationService {
    protected @Inject @ModId String modId;
    protected @Inject MaterialItemNameGenerator materialItemNameGenerator;

    private @Getter Map<String, String> translations = new HashMap<>();

    protected @Inject TranslationService() {}

    public String registerItemTranslation(String name) {
        String id = getId(name);
        translations.put("item." + modId + "." + id, name);
        return id;
    }

    public String registerBlockTranslation(String name) {
        String id = getId(name);
        translations.put("block." + modId + "." + id, name);
        return id;
    }

    public TranslationTextComponent registerTranslation(String name) {
        String id = getId(name);
        translations.put(modId + "." + id, name);
        return new TranslationTextComponent(modId + "." + id);
    }

    public String registerMaterialItem(Component component, Material material) {
        String name = materialItemNameGenerator.generate(component, material);
        if (component instanceof ComponentBlock) {
            return registerBlockTranslation(name);
        } else {
            return registerItemTranslation(name);
        }
    }

    private String getId(String name) {
        return name.toLowerCase().replace(" ", "_");
    }
}
