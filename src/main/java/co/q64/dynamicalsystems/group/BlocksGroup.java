package co.q64.dynamicalsystems.group;

import co.q64.dynamicalsystems.block.extra.ExtraBlocks;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BlocksGroup extends ItemGroup {
    protected @Inject ItemUtil itemUtil;
    protected @Inject ExtraBlocks blocks;

    protected @Inject BlocksGroup() {
        super("blocks");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack createIcon() {
        return new ItemStack(itemUtil.getExtraBlockItem(blocks.textureDebugBlock));
    }
}
