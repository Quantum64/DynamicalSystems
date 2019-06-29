package co.q64.dynamicalsystems;

import co.q64.dynamicalsystems.client.DaggerClientComponent;
import co.q64.dynamicalsystems.server.DaggerServerComponent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(ModInformation.ID)
public class DynamicalSystems {
    private CommonProxy proxy;

    public DynamicalSystems() {
        long start = System.currentTimeMillis();
        System.out.println("Starting construct...");
        proxy = DistExecutor.runForDist(() -> () -> DaggerClientComponent.create().getProxy(), () -> () -> DaggerServerComponent.create().getProxy());
        proxy.initialize();
        System.out.println("Construct completed (" + (System.currentTimeMillis() - start) + " ms)");
    }
}
