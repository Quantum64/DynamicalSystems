package co.q64.dynamicalsystems.util.identifier;

import net.minecraft.util.Identifier;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IdentifierUtil {
    protected @Inject ModIdentifierFactory modIdentifierFactory;

    protected @Inject IdentifierUtil() {}

    public Identifier get(String path) {
        return modIdentifierFactory.create(path);
    }
}
