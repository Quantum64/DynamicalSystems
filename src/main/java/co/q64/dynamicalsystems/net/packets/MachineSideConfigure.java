package co.q64.dynamicalsystems.net.packets;

import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.tile.MachineTile;
import com.google.auto.factory.AutoFactory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

@AutoFactory
public class MachineSideConfigure {
    private BlockPos position;
    private Direction direction;
    private MachineSideConfiguration config;

    protected MachineSideConfigure(PacketBuffer buffer) {
        CompoundNBT tag = buffer.readCompoundTag();
        direction = Direction.valueOf(tag.getString("direction"));
        config = MachineSideConfiguration.valueOf(tag.getString("config"));
        position = BlockPos.fromLong(tag.getLong("position"));
    }

    protected MachineSideConfigure(BlockPos position, Direction direction, MachineSideConfiguration config) {
        this.direction = direction;
        this.config = config;
        this.position = position;
    }

    public void encode(PacketBuffer buffer) {
        CompoundNBT tag = new CompoundNBT();
        tag.putString("direction", direction.name());
        tag.putString("config", config.name());
        tag.putLong("position", position.toLong());
        buffer.writeCompoundTag(tag);
    }

    public void handle(Supplier<Context> context) {
        context.get().enqueueWork(() -> {
            ServerWorld world = context.get().getSender().getServerWorld();
            if (world.isAreaLoaded(position, 1)) {
                TileEntity te = world.getTileEntity(position);
                if (te != null && te instanceof MachineTile) {
                    ((MachineTile) te).updateSide(direction, config);
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}
