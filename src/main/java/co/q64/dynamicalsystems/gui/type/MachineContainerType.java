package co.q64.dynamicalsystems.gui.type;

import co.q64.dynamicalsystems.gui.MachineContainer;
import co.q64.dynamicalsystems.gui.MachineContainerFactory;
import co.q64.dynamicalsystems.tile.MachineTile;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.IContainerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MachineContainerType extends ContainerType<MachineContainer> {
    protected @Inject MachineContainerType(MachineContainerFactory factory, IdentifierUtil identifiers) {
        super(new IContainerFactory<MachineContainer>() {
            public @Override @OnlyIn(Dist.CLIENT) MachineContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
                return factory.create(windowId, inv, (MachineTile) Minecraft.getInstance().world.getTileEntity(data.readBlockPos()));
            }
        });
        setRegistryName(identifiers.get("machine"));
    }
}
