package deepDown.gameObjects;

public class Door extends GameObject {

    private boolean isOpen;

    public Door(double x, double y, int height, int width, boolean isOpen) {
        super(x, y, height, width);
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
