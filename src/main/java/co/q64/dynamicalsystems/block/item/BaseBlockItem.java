package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.BaseBlock;
import co.q64.dynamicalsystems.item.IdentifiableItem;
import lombok.Getter;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

import java.util.Optional;

@Getter
public class BaseBlockItem extends BlockItem implements IdentifiableItem {
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

    @Override
    public Optional<String> getTag() {
        return Optional.empty();
    }
}
