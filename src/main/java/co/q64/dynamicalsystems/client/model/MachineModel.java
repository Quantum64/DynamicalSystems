package co.q64.dynamicalsystems.client.model;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.model.IModelState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@AutoFactory
public class MachineModel implements IDynamicBakedModel {
    private Map<Direction, IBakedModel> baseOff = new HashMap<>();
    private Map<Direction, IBakedModel> baseOn = new HashMap<>();
    private MachineBlockItem machine;
    private MachineSideModels sideModels;
    private IdentifierUtil identifiers;

    protected MachineModel(MachineBlockItem machine, @Provided MachineSideModels machineSideModels, @Provided IdentifierUtil identifiers) {
        this.machine = machine;
        this.sideModels = machineSideModels;
        this.identifiers = identifiers;
    }

    public void bake(ModelLoader loader) {
        for (Direction direction : BlockStateProperties.HORIZONTAL_FACING.getAllowedValues()) {
            baseOff.put(direction, loader.getUnbakedModel(identifiers.get(machine.getOffModel())).bake(loader, ModelLoader.defaultTextureGetter(), new ISprite() {
                public @Override IModelState getState() { return MachineProperties.getRotation(MachineProperties.get(direction)); }
            }, DefaultVertexFormats.BLOCK));
            baseOn.put(direction, loader.getUnbakedModel(identifiers.get(machine.getOnModel())).bake(loader, ModelLoader.defaultTextureGetter(), new ISprite() {
                public @Override IModelState getState() { return MachineProperties.getRotation(MachineProperties.get(direction)); }
            }, DefaultVertexFormats.BLOCK));
        }
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
        Direction direction = state == null ? Direction.NORTH : state.get(MachineProperties.FACING);
        Boolean running = extraData.getData(MachineProperties.RUNNING);
        IBakedModel base = running != null && running == true ? baseOn.get(direction) : baseOff.get(direction);
        List<BakedQuad> result = new ArrayList<>(base.getQuads(state, side, rand, extraData));
        for (ModelProperty<MachineSideConfiguration> property : MachineProperties.SIDES) {
            MachineSideConfiguration config = extraData.getData(property);
            if (config == MachineSideConfiguration.INPUT || config == MachineSideConfiguration.OUTPUT || config == MachineSideConfiguration.BOTH) {
                result.addAll(sideModels.getComponents().get(property).get(config).getQuads(state, side, rand, extraData));
            }
        }
        return result;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false; //TODO ??
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return MissingTextureSprite.func_217790_a();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }
}
