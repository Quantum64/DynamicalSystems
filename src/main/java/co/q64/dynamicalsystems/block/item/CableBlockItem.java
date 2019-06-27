package co.q64.dynamicalsystems.block.item;

import co.q64.dynamicalsystems.block.CableBlock;
import co.q64.dynamicalsystems.block.CableBlockFactory;
import co.q64.dynamicalsystems.group.MaterialsGroup;
import co.q64.dynamicalsystems.item.MaterialItem;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.resource.TranslationService;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@AutoFactory
public class CableBlockItem extends MaterialBlockItem implements MaterialItem {
    private @Getter CableBlock block;

    public CableBlockItem(Material material, Component component, @Provided CableBlockFactoryFactory blockFactoryFactory, @Provided TranslationService service, @Provided MaterialsGroup group) {
        super(material, component, blockFactoryFactory.getFactory().create(service.registerMaterialItem(component, material), component), group);
        this.block = (CableBlock) super.getBaseBlock();
    }

    @Singleton
    public static class CableBlockFactoryFactory {
        protected @Inject CableBlockFactoryFactory() {}

        protected @Getter @Inject CableBlockFactory factory;
    }
}
