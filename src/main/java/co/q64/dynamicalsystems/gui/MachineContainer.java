package co.q64.dynamicalsystems.gui;

import co.q64.dynamicalsystems.gui.type.MachineContainerType;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

@AutoFactory
public class MachineContainer extends Container {
    private PlayerInventory inventory;
    
    protected MachineContainer(int windowId, PlayerInventory inventory, @Provided MachineContainerType type ) {
        super(type, windowId);
        this.inventory = inventory;
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }
}
