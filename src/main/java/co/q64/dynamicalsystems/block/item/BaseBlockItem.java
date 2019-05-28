package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.BaseBlock;
import lombok.Getter;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

@Getter
public class BaseBlockItem extends BlockItem {
	private String name, id;

	public BaseBlockItem(BaseBlock block) {
		this(block, new Settings());
	}

	public BaseBlockItem(BaseBlock block, ItemGroup group) {
		this(block, new Settings().itemGroup(group));
	}

	public BaseBlockItem(BaseBlock block, Settings settings) {
		super(block, settings);
		this.name = block.getName();
		this.id = name.replace(" ", "_").toLowerCase();
	}

	@Override
	public Component getTranslatedNameTrimmed(ItemStack stack) {
		return new TextComponent(name);
	}
	
}
