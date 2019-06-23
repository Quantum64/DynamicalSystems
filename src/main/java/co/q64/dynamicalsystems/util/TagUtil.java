package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import co.q64.dynamicalsystems.unification.SharedItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class TagUtil {
    protected @Inject @SharedNamespace String namespace;
    protected @Inject MaterialItemLoader materialItemLoader;
    protected @Inject IdentifierUtil identifierUtil;

    public @Inject TagUtil() {}

    public Tag<Item> getTag(SharedItem item) {
        return ItemTags.getCollection().getOrCreate(identifierUtil.getSharedIdentifier(item));
    }

    public List<Tag<Item>> getPossibleTags(SharedItem item) {
        return Arrays.asList(getTag(item)); // Potentially add other namespaces... "c", "oredict", "shared" so we can inject our items into their recipes
    }
}
