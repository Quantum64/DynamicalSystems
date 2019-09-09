package co.q64.dynamicalsystems.resource;

import co.q64.dynamicalsystems.qualifier.ConstantQualifiers.Name;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackInfo.IFactory;
import net.minecraft.resources.ResourcePackInfo.Priority;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class GeneratedPackFinder implements IPackFinder {
    protected @Inject Provider<GeneratedResourcePack> generatedResourcePack;
    protected @Inject @Name String modId;

    protected @Inject GeneratedPackFinder() {}

    @Override
    public <T extends ResourcePackInfo> void addPackInfosToMap(Map<String, T> map, IFactory<T> factory) {
        String name = modId + "_virtual";
        T packInfo = ResourcePackInfo.createResourcePack(name, true, () -> generatedResourcePack.get(), factory, Priority.BOTTOM);
        map.put(name, packInfo);
    }
}
