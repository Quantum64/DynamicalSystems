package co.q64.dynamicalsystems.gui;

import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import co.q64.dynamicalsystems.tile.MachineTile;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;

@AutoFactory
public class MachineContainer extends DynamicContainer<MachineContainer> {
    private PlayerInventory inventory;
    private MachineTile tile;

    protected MachineContainer(int windowId, PlayerInventory inventory, MachineTile tile, @Provided MachineContainerType type) {
        super(windowId, inventory, type);
        this.inventory = inventory;
        this.tile = tile;

        setupInventory();
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }
}
