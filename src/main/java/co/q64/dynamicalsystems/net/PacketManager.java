package co.q64.dynamicalsystems.net;

import co.q64.dynamicalsystems.net.packets.MachineSideConfigure;
import co.q64.dynamicalsystems.net.packets.MachineSideConfigureFactory;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import lombok.Getter;
import net.minecraftforge.fml.network.NetworkRegistry.ChannelBuilder;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PacketManager {
    private static final String protocolVersion = "1";

    protected @Getter @Inject MachineSideConfigureFactory machineSideConfigureFactory;

    private @Getter SimpleChannel channel;

    protected @Inject PacketManager(IdentifierUtil identifiers) {
        this.channel = ChannelBuilder.named(identifiers.get("main"))
                .clientAcceptedVersions(protocolVersion::equals)
                .serverAcceptedVersions(protocolVersion::equals)
                .networkProtocolVersion(() -> protocolVersion)
                .simpleChannel();
    }

    public void register() {
        int id = 0;
        channel.registerMessage(id++, MachineSideConfigure.class, (packet, buffer) -> packet.encode(buffer), buffer -> machineSideConfigureFactory.create(buffer), (packet, context) -> packet.handle(context));
    }
}
