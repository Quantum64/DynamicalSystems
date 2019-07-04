package co.q64.dynamicalsystems.unification;

import co.q64.dynamicalsystems.block.item.BaseBlockItem;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.Components;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.material.Materials;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.resource.TranslationService;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags.Wrapper;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class Unification {
    protected @Inject IdentifierUtil identifiers;
    protected @Inject TranslationService translationService;
    protected @Inject MaterialItemLoader materialItemLoader;

    protected @Getter @Inject Materials materials;
    protected @Getter @Inject Components components;

    private Map<ResourceLocation, Tag<Item>> tags = new HashMap<>();

    @Inject
    protected Unification() {}

    public Tag<Item> get(Component component, Material material) {
        return tagSynthesizer(get(identifiers.get(translationService.registerMaterialItem(component, material))), //TODO there has to be a better way
                materialItemLoader.getItem(component, material).map(MaterialItem::getBaseItem).orElse(Items.BARRIER)); //TODO replace with error item
    }

    public Tag<Item> get(BaseItem item) {
        return tagSynthesizer(get(identifiers.get(item.getId())), item);
    }

    public Tag<Item> get(BaseBlockItem item) {
        return tagSynthesizer(get(identifiers.get(item.getId())), item);
    }

    public Tag<Item> get(ResourceLocation id) {
        if (tags.containsKey(id)) {
            return tags.get(id);
        }
        Tag<Item> result = new Wrapper(id);
        tags.put(id, result);
        return result;
    }

    public ItemStack getStack(Component component, Material material, int amount) {
        return getStack(get(component, material), amount);
    }

    public ItemStack getStack(Component component, Material material) {
        return getStack(component, material, 1);
    }

    public ItemStack getStack(BaseItem item, int amount) {
        return getStack(get(item), amount);
    }

    public ItemStack getStack(BaseItem item) {
        return getStack(item, 1);
    }

    public ItemStack getStack(Tag<Item> item, int amount) {
        return new ItemStack(item.getAllElements().iterator().next(), amount); //TODO does this make sense
    }

    public ItemStack getStack(Tag<Item> item) {
        return getStack(item, 1);
    }

    private Tag<Item> tagSynthesizer(Tag<Item> tag, Item defaultItem) {
        if (!tag.getAllElements().isEmpty()) {
            return tag;
        }
        return new SyntheticTag(tag.getId(), defaultItem);
    }

    private static class SyntheticTag extends Tag<Item> {
        private Set<Item> elements;

        public SyntheticTag(ResourceLocation id, Item defaultItem) {
            super(id);
            this.elements = Stream.of(defaultItem).collect(Collectors.toSet());
        }

        @Override
        public Collection<Item> getAllElements() {
            return elements;
        }
    }
}
