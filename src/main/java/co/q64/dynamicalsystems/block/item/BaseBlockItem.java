package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.BaseBlock;
import lombok.Getter;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

@Getter
public class BaseBlockItem extends BlockItem {
    private String itemName, id;

    public BaseBlockItem(BaseBlock block) {
        this(block, new Properties());
    }

    public BaseBlockItem(BaseBlock block, ItemGroup group) {
        this(block, new Properties().group(group));
    }

    public BaseBlockItem(BaseBlock block, Properties settings) {
        super(block, settings);
        this.itemName = block.getName();
        this.id = itemName.replace(" ", "_").toLowerCase();
        setRegistryName(id);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new StringTextComponent(itemName);
    }
}
