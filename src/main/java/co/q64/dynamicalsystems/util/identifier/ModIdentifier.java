package co.q64.dynamicalsystems.util.identifier;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import net.minecraft.util.Identifier;

@AutoFactory
public class ModIdentifier extends Identifier {

	public ModIdentifier(@Provided @ModId String modId, String path) {
		super(modId, path);
	}
}
