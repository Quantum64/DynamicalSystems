package co.q64.dynamicalsystems.tile.type;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.tile.MachineTile;
import co.q64.dynamicalsystems.tile.MachineTileFactory;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import co.q64.dynamicalsystems.util.ItemUtil;
import net.minecraft.tileentity.TileEntityType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class MachineTileType extends TileEntityType<MachineTile> {
    protected @Inject MachineTileType(MachineTileFactory factory, ItemUtil itemUtil, IdentifierUtil identifiers) {
        super(factory::create, itemUtil.getMachineItems().stream().map(MachineBlockItem::getBlock).collect(Collectors.toSet()), null);
        setRegistryName(identifiers.get("machine"));
    }
}
