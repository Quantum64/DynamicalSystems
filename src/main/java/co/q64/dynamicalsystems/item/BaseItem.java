package co.q64.dynamicalsystems.item;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

@Getter
public abstract class BaseItem extends Item {
    private String itemName, id;

    public BaseItem(String name) {
        this(name, new Properties());
    }

    public BaseItem(String name, ItemGroup group) {
        this(name, new Properties().group(group));
    }

    public BaseItem(String name, Properties settings) {
        super(settings);
        this.itemName = name;
        this.id = name.replace(" ", "_").toLowerCase();
        setRegistryName(id);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new StringTextComponent(itemName);
    }
}
