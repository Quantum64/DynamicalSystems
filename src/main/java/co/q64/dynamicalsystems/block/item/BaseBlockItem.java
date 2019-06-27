package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.BaseBlock;
import lombok.Getter;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

@Getter
public class BaseBlockItem extends BlockItem {
    private BaseBlock block;
    private String id;

    public BaseBlockItem(BaseBlock block) {
        this(block, new Properties());
    }

    public BaseBlockItem(BaseBlock block, ItemGroup group) {
        this(block, new Properties().group(group));
    }

    public BaseBlockItem(BaseBlock block, Properties settings) {
        super(block, settings);
        this.block = block;
        this.id = block.getId();
        setRegistryName(id);
    }

    @Override
    public BaseBlock getBlock() {
        return block;
    }
}
