package co.q64.dynamicalsystems.block.extra;

import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ExtraBlockRegistry {
    protected @Getter List<BlockDefinition> blocks = new ArrayList<>();

    protected @Inject ExtraBlockRegistry() {}

    public void register(BlockDefinition definition) {
        blocks.add(definition);
    }
}
