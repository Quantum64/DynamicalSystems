package co.q64.dynamicalsystems.block;

import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.machine.Machine;
import com.google.auto.factory.AutoFactory;
import lombok.Getter;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@AutoFactory
public class MachineBlock extends BaseBlock {
    private Machine machine;
    private Voltage voltage;

    public MachineBlock(String name, Properties settings) {
        super(name, settings);
    }

    public MachineBlock(String name) {
        super(name);
    }

    public void setMachine(Machine machine, Voltage voltage) {
        this.machine = machine;
        this.voltage = voltage;
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
