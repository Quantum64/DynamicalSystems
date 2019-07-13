package co.q64.dynamicalsystems.client.gui.panel;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public abstract class Panel {
    protected @Getter boolean left = true;
    protected @Getter int color = 0xffff0000;
    protected @Getter ItemStack icon = new ItemStack(Items.STONE);
    protected @Getter int expandedWidth = 80, expandedHeight = 80;

    protected @Getter @Setter boolean expanded = false;

    public void click(int x, int y) {}

    public void render(int x, int y) {}
}
