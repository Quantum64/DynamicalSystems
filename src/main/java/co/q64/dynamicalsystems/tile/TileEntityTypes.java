package co.q64.dynamicalsystems.tile;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.util.ItemUtil;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TileEntityTypes {
    protected @Inject ItemUtil itemUtil;
    protected @Inject MachineTileFactory machineTileFactory;

    private @Getter List<TileEntityType<?>> types = new ArrayList<>();
    private @Getter TileEntityType<MachineTile> machineTileTileEntityType;

    protected @Inject TileEntityTypes() {}

    @Inject
    protected void load() {
        machineTileTileEntityType = Builder.create(machineTileFactory::create, itemUtil.getMachineItems().stream().map(MachineBlockItem::getBlock).toArray(Block[]::new)).build(null);
        machineTileTileEntityType.setRegistryName("machine");
        types.add(machineTileTileEntityType);
    }
}
