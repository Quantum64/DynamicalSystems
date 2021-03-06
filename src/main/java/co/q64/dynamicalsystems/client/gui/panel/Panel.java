package co.q64.dynamicalsystems.client.gui.panel;

import co.q64.dynamicalsystems.resource.Translations;
import com.google.auto.factory.Provided;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;

import javax.inject.Inject;

public abstract class Panel {
    protected @Inject Translations translations;

    protected @Getter boolean left = true;
    protected @Getter int color = 0xffff0000;
    protected @Getter ItemStack icon = new ItemStack(Items.STONE);
    protected @Getter int expandedWidth = 80, expandedHeight = 80;
    protected @Getter ITextComponent translatedName;

    protected @Getter @Setter boolean expanded = false;

    public void click(int x, int y) {}

    public void render(int x, int y) {}
}
