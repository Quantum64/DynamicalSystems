package co.q64.dynamicalsystems.item;

import co.q64.dynamicalsystems.group.MaterialsGroup;
import co.q64.dynamicalsystems.material.MaterialItemNameGenerator;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

@Getter
@AutoFactory
public class SimpleMaterialItem extends BaseItem implements MaterialItem {
    private Material material;
    private Component component;

    public SimpleMaterialItem(Component component, Material material, @Provided MaterialItemNameGenerator generator, @Provided MaterialsGroup group) {
        super(generator.generate(component, material), group);
        this.material = material;
        this.component = component;
    }
}
