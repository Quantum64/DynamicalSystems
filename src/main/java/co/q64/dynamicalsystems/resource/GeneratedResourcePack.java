package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.binders.ConstantBinders.ModId;
import co.q64.dynamicalsystems.binders.ConstantBinders.Name;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackFileNotFoundException;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class GeneratedResourcePack extends ResourcePack {
    protected @Inject ResourcePackGenerator generator;
    protected @Inject IdentifierUtil identifierUtil;
    protected @Inject @ModId String modId;
    protected @Inject @Name String name;

    public @Inject GeneratedResourcePack(@ModId String modId) {
        super(new File(modId + "_virtual_resource_pack"));
    }

    @Override
    public InputStream getResourceStream(ResourcePackType type, ResourceLocation location) throws IOException {
        if (!generator.getVirtualResourcePack().containsKey(location)) {
            throw new ResourcePackFileNotFoundException(new File(name), name);
        }
        return new ByteArrayInputStream(generator.getVirtualResourcePack().get(location));
    }

    @Override
    protected InputStream getInputStream(String name) throws IOException {
        if (!generator.getVirtualResourcePack().containsKey(identifierUtil.get(name))) {
            throw new ResourcePackFileNotFoundException(new File(name), name);
        }
        return new ByteArrayInputStream(generator.getVirtualResourcePack().get(identifierUtil.get(name)));
    }

    @Override
    public boolean resourceExists(ResourcePackType type, ResourceLocation location) {
        return generator.getVirtualResourcePack().containsKey(location);
    }

    @Override
    public Collection<ResourceLocation> getAllResourceLocations(ResourcePackType type, String path, int maxDepth, Predicate<String> filter) {
        List<ResourceLocation> result = new ArrayList<>();
        String target = path + "/";
        for (ResourceLocation key : generator.getVirtualResourcePack().keySet()) {
            if (key.getPath().startsWith(target)) {
                if (filter.test(key.getPath().substring(target.length()))) {
                    result.add(key);
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getResourceNamespaces(ResourcePackType type) {
        return Stream.of(modId, "forge").collect(Collectors.toSet());
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public String getName() {
        return name + " Generated Assets";
    }

    @Override
    public void close() throws IOException {}

    @Override
    protected boolean resourceExists(String s) { return false; }
}
