package co.q64.dynamicalsystems.group;

import co.q64.dynamicalsystems.material.Components;
import co.q64.dynamicalsystems.material.Materials;
import co.q64.dynamicalsystems.unification.Unification;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MaterialsGroup extends ItemGroup {
    protected @Inject Unification unification;
    protected @Inject Materials materials;
    protected @Inject Components components;

    protected @Inject MaterialsGroup() {
        super("materials");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack createIcon() {
        return unification.getStack(components.screw, materials.gold).getItemStack();
    }
}
