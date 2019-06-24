package co.q64.dynamicalsystems.resource;

import net.minecraft.resources.FolderPack;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GeneratedResourcePack extends FolderPack {
    public @Inject GeneratedResourcePack(ResourcePackGenerator generator) {
        super(generator.getResourcePackFolder());
    }
}
