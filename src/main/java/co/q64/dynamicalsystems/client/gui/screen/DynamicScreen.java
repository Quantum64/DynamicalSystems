package co.q64.dynamicalsystems.client.gui.screen;

import co.q64.dynamicalsystems.client.gui.ModularGuiRender;
import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.gui.DynamicContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import lombok.Getter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class DynamicScreen<T extends DynamicContainer> extends ContainerScreen<T> {
    private @Getter List<Panel> panels = new ArrayList<>();
    private @Getter T container;
    private ModularGuiRender render;

    public DynamicScreen(T container, PlayerInventory playerInventory, ITextComponent title, ModularGuiRender render) {
        super(container, playerInventory, title);
        this.container = container;
        this.render = render;
    }

    public boolean mouseClicked(double x, double y, int p_mouseClicked_5_) {
        if (render.click(this, x, y, p_mouseClicked_5_)) {
            return true;
        }
        return super.mouseClicked(x, y, p_mouseClicked_5_);
    }

    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        this.renderBackground();
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }

    public ItemRenderer getItemRenderer() {
        return itemRenderer;
    }

    public void drawItemStack(ItemStack stack, int x, int y) {
        GlStateManager.translatef(0.0F, 0.0F, 32.0F);
        this.blitOffset = 200;
        this.itemRenderer.zLevel = 200.0F;
        FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = this.font;
        this.itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
        //this.itemRenderer.renderItemOverlayIntoGUI(font, stack, x, y - (this.draggedStack.isEmpty() ? 0 : 8), altText);
        this.blitOffset = 0;
        this.itemRenderer.zLevel = 0.0F;
    }
}
