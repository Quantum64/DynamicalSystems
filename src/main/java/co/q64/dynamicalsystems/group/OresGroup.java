package co.q64.dynamicalsystems.group;

import co.q64.dynamicalsystems.material.Components;
import co.q64.dynamicalsystems.material.Materials;
import co.q64.dynamicalsystems.unification.Unification;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OresGroup extends ItemGroup {
    protected @Inject Unification unification;
    protected @Inject Materials materials;
    protected @Inject Components components;

    protected @Inject OresGroup() {
        super("ores");
    }

    @Override
    public ItemStack createIcon() {
        return unification.getStack(components.stoneOre, materials.iron).getItemStack();
    }
}
