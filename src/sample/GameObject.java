package sample;

public abstract class GameObject {
    private double x;
    private double y;
    private int h;
    private int w;

    public GameObject (double x, double y, int height, int width){
        this.x = x;
        this.y = y;
        this.h = height;
        this.w = width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x += x;
    }

    public void setY(double y) {
        this.y += y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}
