package co.q64.dynamicalsystems.client.gui.panel.machine;

import co.q64.dynamicalsystems.client.gui.GuiDynamicRender;
import co.q64.dynamicalsystems.client.gui.panel.Panel;
import co.q64.dynamicalsystems.client.gui.screen.MachineScreen;
import co.q64.dynamicalsystems.client.texture.MachineTextureMap;
import co.q64.dynamicalsystems.grid.energy.Voltage;
import co.q64.dynamicalsystems.gui.MachineContainer;
import co.q64.dynamicalsystems.machine.Machine;
import co.q64.dynamicalsystems.machine.MachineSideConfiguration;
import co.q64.dynamicalsystems.tile.MachineTile;
import co.q64.dynamicalsystems.unification.Unification;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

import java.util.Map;
import java.util.Map.Entry;

@AutoFactory
public class SidePanel extends Panel {
    private static final int PADDING = 4;
    private static final int WIDTH = 16;

    private MachineScreen screen;
    private GuiDynamicRender render;
    private MachineTextureMap machineTextureMap;

    protected SidePanel(MachineScreen screen, @Provided MachineTextureMap machineTextureMap, @Provided Unification unification, @Provided GuiDynamicRender render) {
        this.icon = new ItemStack(unification.getStack(unification.getComponents().gear, unification.getMaterials().iron).getItem());
        this.screen = screen;
        this.render = render;
        this.machineTextureMap = machineTextureMap;
    }

    public void render(int x, int y) {
        MachineContainer container = screen.getContainer();
        MachineTile tile = container.getTile();
        Machine machine = tile.getMachine();
        Voltage voltage = tile.getVoltage();
        for (Entry<Direction, MachineSideConfiguration> entry : container.getTile().getSideConfigurations().entrySet()) {
            Point point = getRenderLocation(entry.getKey());
            render.rect(machineTextureMap.getTexturePath(machineTextureMap.getMachineCasingTexture(machine, voltage, entry.getValue(), tile.running())),
                    x + point.getX(), y + point.getY(), 16, 16, 0xFFFFFFFF);
            if (entry.getValue() != MachineSideConfiguration.DISABLED) {
                render.rect(machineTextureMap.getTexturePath(machineTextureMap.getMachineOverlayTexture(machine, voltage, entry.getValue(), tile.running())),
                        x + point.getX(), y + point.getY(), 16, 16, 0xFFFFFFFF);
            }
        }
    }

    private Point getRenderLocation(Direction direction) {
        RelativeDirection relative = RelativeDirection.FRONT;
        Direction front = Direction.NORTH;
        Map<Direction, MachineSideConfiguration> sides = screen.getContainer().getTile().getSideConfigurations();
        for (Entry<Direction, MachineSideConfiguration> side : sides.entrySet()) {
            if (side.getValue() == MachineSideConfiguration.FRONT) {
                front = side.getKey();
                break;
            }
        }
        switch (direction) {
            case DOWN:
                relative = RelativeDirection.DOWN;
                break;
            case UP:
                relative = RelativeDirection.UP;
                break;
            case NORTH:
                switch (front) {
                    case NORTH:
                        relative = RelativeDirection.FRONT;
                        break;
                    case SOUTH:
                        relative = RelativeDirection.BACK;
                        break;
                    case WEST:
                        relative = RelativeDirection.RIGHT;
                        break;
                    case EAST:
                        relative = RelativeDirection.LEFT;
                        break;
                }
                break;
            case SOUTH:
                switch (front) {
                    case NORTH:
                        relative = RelativeDirection.BACK;
                        break;
                    case SOUTH:
                        relative = RelativeDirection.FRONT;
                        break;
                    case WEST:
                        relative = RelativeDirection.LEFT;
                        break;
                    case EAST:
                        relative = RelativeDirection.RIGHT;
                        break;
                }
                break;
            case WEST:
                switch (front) {
                    case NORTH:
                        relative = RelativeDirection.LEFT;
                        break;
                    case SOUTH:
                        relative = RelativeDirection.RIGHT;
                        break;
                    case WEST:
                        relative = RelativeDirection.FRONT;
                        break;
                    case EAST:
                        relative = RelativeDirection.BACK;
                        break;
                }
                break;
            case EAST:
                switch (front) {
                    case NORTH:
                        relative = RelativeDirection.RIGHT;
                        break;
                    case SOUTH:
                        relative = RelativeDirection.LEFT;
                        break;
                    case WEST:
                        relative = RelativeDirection.BACK;
                        break;
                    case EAST:
                        relative = RelativeDirection.FRONT;
                        break;
                }
                break;
        }
        switch (relative) {
            case DOWN:
                return new Point(WIDTH + PADDING * 2, PADDING * 3 + WIDTH * 2);
            case UP:
                return new Point(WIDTH + PADDING * 2, PADDING);
            case RIGHT:
                return new Point(WIDTH * 2 + PADDING * 3, PADDING * 2 + WIDTH);
            case LEFT:
                return new Point(PADDING, PADDING * 2 + WIDTH);
            case FRONT:
                return new Point(WIDTH + PADDING * 2, PADDING * 2 + WIDTH);
            case BACK:
                return new Point(WIDTH * 2 + PADDING * 3, PADDING * 3 + WIDTH * 2);
        }
        return new Point(0, 0);
    }

    @Data
    @AllArgsConstructor
    private static class Point {
        private int x, y;
    }

    private static enum RelativeDirection {
        UP, DOWN, LEFT, RIGHT, FRONT, BACK;
    }
}
