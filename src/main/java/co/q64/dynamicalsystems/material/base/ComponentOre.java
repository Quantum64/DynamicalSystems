package co.q64.dynamicalsystems.material.base;

import lombok.Getter;

@Getter
public abstract class ComponentOre extends ComponentBlock {
    protected String baseTexture = "";

    protected ComponentOre() {
        model = "block/block_ore";
        name = "Ore";
        textureName = "ore";
    }
}
