package mk.ukim.finki.kol2;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Component {
    private String color;
    private int weight;
    private Set<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.components = new TreeSet<>(Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color, int weight) {
        if (this.weight < weight)
            this.color = color;
        for (Component component : components)
            component.setColor(color, weight);
    }

    public int getWeight() {
        return weight;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public String toString(String padding) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%d:%s\n", padding, weight, color));
        for (Component component : components)
            sb.append(component.toString(padding + "---"));
        return sb.toString();
    }
}
