package co.q64.dynamicalsystems.loader;

import co.q64.dynamicalsystems.material.Components;
import co.q64.dynamicalsystems.material.Materials;
import co.q64.dynamicalsystems.unification.Unification;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UnificationLoader {
    //protected @Inject ServerDataUnifier serverDataUnifier;
    protected @Inject Components components;
    protected @Inject Materials materials;
    protected @Inject Unification unification;

    protected @Inject UnificationLoader() {}

    public void load() {
        //serverDataUnifier.register();

       // unification.get(components.ingot, materials.gold).unify(Items.GOLD_INGOT);
        //unification.get(components.ingot, materials.iron).unify(Items.IRON_INGOT);
    }
}
