package co.q64.dynamicalsystems.material.components;

import co.q64.dynamicalsystems.material.base.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScrapComponent extends Component {
    protected @Inject ScrapComponent() {
        name = "Scrap";
        textureName = "scrap";
        generate = material -> true;
    }
}
