package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.ExtraBlock;
import co.q64.dynamicalsystems.block.ExtraBlockFactory;
import co.q64.dynamicalsystems.block.extra.BlockDefinition;
import co.q64.dynamicalsystems.group.BlocksGroup;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@AutoFactory
public class ExtraBlockItem extends BaseBlockItem {
    private @Getter BlockDefinition definition;
    private ExtraBlock block;

    protected ExtraBlockItem(BlockDefinition definition, @Provided ExtraBlockFactoryFactory blockFactoryFactory, @Provided MaterialItemNameGenerator generator, @Provided BlocksGroup group) {
        super(blockFactoryFactory.getFactory().create(definition), new Properties().group(group));
        this.definition = definition;
        this.block = (ExtraBlock) super.getBlock();
    }

    @Override
    public ExtraBlock getBlock() {
        return block;
    }

    @Singleton
    public static class ExtraBlockFactoryFactory {
        protected @Inject ExtraBlockFactoryFactory() {}

        protected @Getter @Inject ExtraBlockFactory factory;
    }
}
