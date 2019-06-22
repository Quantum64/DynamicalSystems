package co.q64.dynamicalsystems.util;

import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemLoader;
import net.minecraft.util.Identifier;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ItemUtil {
    protected @Inject MaterialItemLoader materialItemLoader;

    protected @Inject ItemUtil() {}

    public List<MaterialItem> getMaterialItems() {
        return materialItemLoader.getItems();
    }

    public Optional<MaterialItem> getMaterialItem(Identifier identifier) {
        return Optional.ofNullable(materialItemLoader.getIdentifierCache().get(identifier));
    }
}
