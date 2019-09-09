package co.q64.dynamicalsystems.client.model;

import co.q64.dynamicalsystems.block.item.MachineBlockItem;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.state.MachineProperties;
import co.q64.dynamicalsystems.util.IdentifierUtil;
import com.google.auto.factory.Provided;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MachineModel implements IDynamicBakedModel {
    protected @Inject MachineSideModels sideModels;
    protected @Inject IdentifierUtil identifiers;
    private MachineBlockItem machine;

    protected @Inject MachineModel() {}

    public MachineModel setup(MachineBlockItem machine) {
        this.machine = machine;
        return this;
    }

    public void bake(ModelLoader loader) {
        // no-op
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
        Direction direction = state == null ? Direction.NORTH : state.get(MachineProperties.FACING);
        Boolean running = extraData.getData(MachineProperties.RUNNING);
        IBakedModel base = running != null && running == true ?
                sideModels.getFrontOn().get(MachineProperties.get(direction)).get(machine.getMachine()).get(machine.getVoltage()) :
                sideModels.getFrontOff().get(MachineProperties.get(direction)).get(machine.getMachine()).get(machine.getVoltage());
        List<BakedQuad> baseQuads = base.getQuads(state, side, rand, extraData);
        List<BakedQuad> result = new ArrayList<>();
        result.addAll(baseQuads);
        for (ModelProperty<MachineSideConfiguration> property : MachineProperties.SIDES) {
            MachineSideConfiguration config = extraData.getData(property);
            if (config == MachineSideConfiguration.INPUT || config == MachineSideConfiguration.OUTPUT || config == MachineSideConfiguration.BOTH || config == MachineSideConfiguration.DISABLED) {
                result.addAll(sideModels.getSides().get(property).get(config).get(machine.getVoltage()).getQuads(state, side, rand, extraData));
            }
        }
        return result;
    }

    @Override
    public IModelData getModelData(IEnviromentBlockReader world, BlockPos pos, BlockState state, IModelData data) {
        for (ModelProperty<MachineSideConfiguration> property : MachineProperties.SIDES) {
            Direction direction = state.get(MachineProperties.FACING);
            if (MachineProperties.getDirection(property) == direction) {
                continue;
            }
            if (!data.hasProperty(property)) {
                data.setData(property, MachineSideConfiguration.DISABLED);
            }
        }
        return data;
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
