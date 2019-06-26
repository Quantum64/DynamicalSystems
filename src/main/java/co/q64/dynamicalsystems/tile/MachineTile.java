package co.q64.dynamicalsystems.tile;

import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.state.MachineProperties;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

@AutoFactory
public class MachineTile extends TileEntity implements ITickableTileEntity {

    private LazyOptional<ItemStackHandler> itemHandler = LazyOptional.of(this::createItemHandler);

    public MachineTile(@Provided TileEntityTypes types) {
        super(types.getMachineTileTileEntityType());
    }

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler();
    }

    @Override
    public void read(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> handler.deserializeNBT(tag));
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        itemHandler.ifPresent(handler -> tag.put("inventory", handler.serializeNBT()));
        return super.write(tag);
    }

    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
                .withInitial(MachineProperties.get(getBlockState().get(MachineProperties.FACING)), MachineSideConfiguration.FRONT)
                .withInitial(MachineProperties.UP, MachineSideConfiguration.INPUT)
                .withInitial(MachineProperties.DOWN, MachineSideConfiguration.OUTPUT)
                .withInitial(MachineProperties.RUNNING, false)
                .build();
    }

    @Override
    public void tick() {

    }
}
