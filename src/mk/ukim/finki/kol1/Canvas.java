package mk.ukim.finki.kol1;

import java.util.ArrayList;

class Canvas {
    private ArrayList<Shape> shapes;

    public Canvas() {
        shapes = new ArrayList<>();
    }

    void add(String id, Color color, float radius) {
        int i;
        Circle c = new Circle(id, color, radius);
        for (i = 0; i < shapes.size(); ++i)
            if (c.weight() > shapes.get(i).weight())
                break;
        shapes.add(i, c);
    }

    void add(String id, Color color, float width, float height) {
        int i;
        Rectangle r = new Rectangle(id, color, width, height);
        for (i = 0; i < shapes.size(); ++i)
            if (r.weight() > shapes.get(i).weight())
                break;
        shapes.add(i, r);
    }

    void scale(String id, float scaleFactor) {
        for (int i = 0; i < shapes.size(); ++i) {
            if (shapes.get(i).getId().compareTo(id) == 0) {
                Shape temp = shapes.get(i);
                shapes.remove(shapes.get(i));
                temp.scale(scaleFactor);
                int type = temp.getType();
                if (type == 1) {
                    Circle c = (Circle) temp;
                    add(c.getId(), c.getColor(), c.getRadius());
                } else if (type == 2) {
                    Rectangle r = (Rectangle) temp;
                    add(r.getId(), r.getColor(), r.getWidth(), r.getHeight());
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : shapes) {
            int type = shape.getType();
            if (type == 1) {
                Circle c = (Circle) shape;
                sb.append(String.format("C: %-5s%-10s%10.2f\n", c.getId(), c.getColor(), c.weight()));
            } else if (type == 2) {
                Rectangle r = (Rectangle) shape;
                sb.append(String.format("R: %-5s%-10s%10.2f\n", r.getId(), r.getColor(), r.weight()));
            }
        }
        return sb.toString();
    }
}
