package co.q64.dynamicalsystems.client;

import co.q64.dynamicalsystems.client.listener.ClientRegistryListener;
import co.q64.dynamicalsystems.listener.Listener;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface ClientModule {
    // @formatter:off
    @Binds @IntoSet Listener bindClientRegistryListener(ClientRegistryListener clientRegistryListener);
    // @formatter:on
}
