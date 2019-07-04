package co.q64.dynamicalsystems.client.gui.panel;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public abstract class Panel {
    protected @Getter boolean left = true, expanded = false;
    protected @Getter int color = 0xffff0000;
    protected @Getter Item icon = Items.STONE;
    protected @Getter int expandedWidth = 300, expandedHeight = 300;
}
