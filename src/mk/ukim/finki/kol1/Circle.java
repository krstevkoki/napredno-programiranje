package mk.ukim.finki.kol1;

class Circle extends Shape {
    private float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.pow(radius, 2) * Math.PI);
    }

    @Override
    int getType() {
        return 1;
    }
}
