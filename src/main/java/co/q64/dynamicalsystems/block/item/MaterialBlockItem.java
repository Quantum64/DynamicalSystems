package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.BaseBlock;
import co.q64.dynamicalsystems.block.MaterialBlock;
import co.q64.dynamicalsystems.block.MaterialBlockFactory;
import co.q64.dynamicalsystems.group.MaterialsGroup;
import co.q64.dynamicalsystems.group.OresGroup;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.ComponentOre;
import co.q64.dynamicalsystems.material.base.Material;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;
import net.minecraft.item.ItemGroup;

import javax.inject.Inject;
import javax.inject.Singleton;

@AutoFactory
public class MaterialBlockItem extends BaseBlockItem implements MaterialItem {
    private Component component;
    private Material material;
    private MaterialBlock block;

    protected MaterialBlockItem(Material material, Component component, @Provided MaterialBlockFactoryFactory blockFactoryFactory, @Provided MaterialItemNameGenerator generator, @Provided MaterialsGroup materialsGroup, @Provided OresGroup oresGroup) {
        super(blockFactoryFactory.getFactory().create(generator.generate(component, material)), new Properties().group(component instanceof ComponentOre ? oresGroup : materialsGroup));
        this.block = (MaterialBlock) super.getBlock();
        this.component = component;
        this.material = material;
        this.block.setItem(this);
    }

    protected MaterialBlockItem(Material material, Component component, BaseBlock block, ItemGroup group) {
        super(block, new Properties().group(group));
        this.block = (MaterialBlock) super.getBlock();
        this.component = component;
        this.material = material;
        this.block.setItem(this);
    }

    @Override
    public MaterialBlock getBaseBlock() {
        return block;
    }

    @Override
    public Component getComponent() {
        return component;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Singleton
    public static class MaterialBlockFactoryFactory {
        protected @Inject MaterialBlockFactoryFactory() {}

        protected @Getter @Inject MaterialBlockFactory factory;
    }
}
