package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ChunkComponent extends Component {
    protected @Inject ChunkComponent() {
        name = "Chunk";
        textureName = "chunk";
        generate = material -> true;
    }
}
