package co.q64.dynamicalsystems.client.loader;

import co.q64.dynamicalsystems.block.MaterialBlock;
import co.q64.dynamicalsystems.block.item.MaterialBlockItem;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.item.SimpleMaterialItem;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ColorHandlerLoader {
    protected @Inject ItemUtil itemUtil;

    protected @Inject ColorHandlerLoader() {}

    public void registerBlockColors(BlockColors blockColors) {
        blockColors.register((state, view, position, layer) -> {
            return ((MaterialBlock) state.getBlock()).getItem().getMaterial().getColor();
        }, itemUtil.getMaterialItems().stream().filter(MaterialItem::isBlock).map(MaterialItem::getBaseBlock).toArray(Block[]::new));
    }

    public void registerItemColors(ItemColors itemColors) {
        itemColors.register((item, layer) -> {
            if (layer == 0) {
                return ((SimpleMaterialItem) item.getItem()).getMaterial().getColor();
            }
            return 0xFFFFFF;
        }, itemUtil.getMaterialItems().stream().filter(MaterialItem::isItem).toArray(SimpleMaterialItem[]::new));

        itemColors.register((item, layer) -> {
            if (layer == 0) {
                return ((MaterialBlockItem) item.getItem()).getMaterial().getColor();
            }
            return 0xFFFFFF;
        }, itemUtil.getMaterialItems().stream().filter(MaterialItem::isBlock).toArray(MaterialBlockItem[]::new));
    }
}
