package deepDown.gameObjects;

public class Door extends GameObject {

    private boolean open;

    public Door(double x, double y, int height, int width, boolean open) {
        super(x, y, height, width);
        this.open = open;
    }
}
