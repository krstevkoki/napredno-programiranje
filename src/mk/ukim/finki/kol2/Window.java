package mk.ukim.finki.kol2;

import java.util.Map;
import java.util.TreeMap;

class Window {
    private String name;
    private Map<Integer, Component> components;

    public Window(String name) {
        this.name = name;
        this.components = new TreeMap<>();
    }

    public void addComponent(int position, Component component) throws InvalidPositionException {
        if (components.containsKey(position))
            throw new InvalidPositionException(String.format("Invalid position %d, alredy taken!", position));
        components.put(position, component);
    }

    public void changeColor(int weight, String color) {
        for (Integer position : components.keySet())
            components.get(position).setColor(color, weight);
    }

    public void swichComponents(int pos1, int pos2) {
        Component temp = components.getOrDefault(pos1, null);
        if (temp != null) {
            components.computeIfPresent(pos1, (key, value) -> {
                value = components.get(pos2);
                return value;
            });
            components.computeIfPresent(pos2, (key, value) -> {
                value = temp;
                return value;
            });
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("WINDOW %s\n", name));
        for (Integer position : components.keySet())
            sb.append(String.format("%d:%s", position, components.get(position).toString("")));
        return sb.toString();
    }
}
