package co.q64.dynamicalsystems.item;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

@Getter
public abstract class BaseItem extends Item {
	private String name;

	public BaseItem(String name) {
		super(new Settings());
		this.name = name;
	}

	public BaseItem(String name, Settings settings) {
		super(settings);
		this.name = name;
	}
	
	@Override
	public Component getTranslatedNameTrimmed(ItemStack stack) {
		return new TextComponent(name);
	}
}
