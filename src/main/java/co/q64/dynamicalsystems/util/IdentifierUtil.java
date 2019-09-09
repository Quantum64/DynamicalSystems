package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.qualifier.ConstantQualifiers.SharedNamespace;
import co.q64.dynamicalsystems.item.BaseItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.util.identifier.ModIdentifierFactory;
import net.minecraft.util.ResourceLocation;

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
}
