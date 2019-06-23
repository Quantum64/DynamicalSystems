package co.q64.dynamicalsystems.block;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@Getter
public class BaseBlock extends Block {
    private String name, id;

    public BaseBlock(String name) {
        this(name, Properties.create(Material.IRON));
    }

    public BaseBlock(String name, Material material) {
        this(name, Properties.create(Material.IRON));
    }

    public BaseBlock(String name, Properties settings) {
        super(settings);
        this.name = name;
        this.id = name.replace(" ", "_").toLowerCase();
        setRegistryName(id);
    }
}
