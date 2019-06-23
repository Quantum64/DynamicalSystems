package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.binders.ConstantBinders.SharedNamespace;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.unification.SharedItem;
import co.q64.dynamicalsystems.util.identifier.ModIdentifierFactory;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IdentifierUtil {
    protected @Inject ModIdentifierFactory identifierUtil;
    protected @Inject @SharedNamespace String shared;
    protected @Inject MaterialItemNameGenerator materialItemNameGenerator;

    protected @Inject IdentifierUtil() {}

    public ResourceLocation getIdentifier(BaseItem item) {
        return identifierUtil.create(item.getId());
    }

    public ResourceLocation getIdentifier(MaterialItem item) {
        return identifierUtil.create(item.getId());
    }

    public ResourceLocation get(String path) {
        return identifierUtil.create(path);
    }

    public ResourceLocation getSharedIdentifier(SharedItem si) {
        if (si.getComponent().isPresent() && si.getMaterial().isPresent()) {
            return new ResourceLocation(shared, materialItemNameGenerator.generateId(si.getComponent().get(), si.getMaterial().get()));
        }
        for (Item item : si.getItems()) {
            if (item instanceof BaseItem) {
                return new ResourceLocation(shared, ((BaseItem) item).getId());
            }
        }
        if (!si.getFirst().isPresent()) {
            throw new IllegalStateException("SharedItem is empty!"); // Probably impossible
        }
        //TODO fix
        return new ResourceLocation(shared, Registry.ITEM.getKey(si.getFirst().get()).getPath());
    }
}
