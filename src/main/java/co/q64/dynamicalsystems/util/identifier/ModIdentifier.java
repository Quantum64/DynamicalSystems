package co.q64.dynamicalsystems.util.identifier;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.util.ResourceLocation;

@AutoFactory
public class ModIdentifier extends ResourceLocation {

    public ModIdentifier(@Provided @ModId String modId, String path) {
        super(modId, path);
    }
}
