package co.q64.dynamicalsystems.block;

import lombok.Getter;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

@Getter
public class BaseBlock extends Block {
    private String name, id;

    public BaseBlock(String name) {
        this(name, FabricBlockSettings.of(Material.METAL).build());
    }

    public BaseBlock(String name, Material material) {
        this(name, FabricBlockSettings.of(material).build());
    }

    public BaseBlock(String name, Settings settings) {
        super(settings);
        this.name = name;
        this.id = name.replace(" ", "_").toLowerCase();
    }
}
