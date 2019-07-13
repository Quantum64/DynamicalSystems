package co.q64.dynamicalsystems.client.gui;

import co.q64.dynamicalsystems.util.Point;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DefaultMachineLayout {
    private Map<Integer, List<Point>> inputSlots = new HashMap<>();
    private Map<Integer, List<Point>> outputSlots = new HashMap<>();

    @Inject
    protected DefaultMachineLayout() {
        inputSlots.put(1, Arrays.asList(new Point(44, 33)));
        inputSlots.put(2, Arrays.asList(new Point(22, 33), new Point(44, 33)));

        outputSlots.put(1, Arrays.asList(new Point(114, 33)));
        outputSlots.put(2, Arrays.asList(new Point(114, 33), new Point(136, 33)));
    }

    public List<Point> getInputSlotLocations(int count) {
        return getOrDelegate(count, inputSlots);
    }

    public List<Point> getOutputSlotLocations(int count) {
        return getOrDelegate(count, outputSlots);
    }

    private List<Point> getOrDelegate(int index, Map<Integer, List<Point>> map) {
        return map.computeIfAbsent(index, i -> {
            List<Point> result = new ArrayList<>();
            result.addAll(getOrDelegate(i - 1, map));
            result.add(new Point(0, 0));
            return result;
        });
    }
}
