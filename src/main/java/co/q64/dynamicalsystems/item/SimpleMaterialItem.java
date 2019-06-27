package co.q64.dynamicalsystems.item;

import co.q64.dynamicalsystems.group.MaterialsGroup;
import co.q64.dynamicalsystems.material.base.Component;
import co.q64.dynamicalsystems.material.base.Material;
import co.q64.dynamicalsystems.resource.TranslationService;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.Getter;

@Getter
@AutoFactory
public class SimpleMaterialItem extends BaseItem implements MaterialItem {
    private Material material;
    private Component component;

    public SimpleMaterialItem(Component component, Material material, @Provided TranslationService service, @Provided MaterialsGroup group) {
        super(service.registerMaterialItem(component, material), group);
        this.material = material;
        this.component = component;
    }
}
