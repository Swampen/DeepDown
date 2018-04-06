package sample;

public class Exit extends GameObject {

    private boolean open;

    public Exit(double x, double y, int height, int width, boolean open) {
        super(x, y, height, width);
        this.open = open;
    }
}
