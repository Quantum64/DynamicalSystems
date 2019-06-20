package co.q64.dynamicalsystems.item;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

@Getter
public abstract class BaseItem extends Item {
	private String itemName, id;

	public BaseItem(String name) {
		this(name, new Settings());
	}

	public BaseItem(String name, ItemGroup group) {
		this(name, new Settings().group(group));
	}

	public BaseItem(String name, Settings settings) {
		super(settings);
		this.itemName = name;
		this.id = name.replace(" ", "_").toLowerCase();
	}

	
	@Override
	public Component getName(ItemStack stack) {
		return new TextComponent(itemName);
	}
}
