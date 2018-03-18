package sample;

public abstract class GameObject {
    protected double x;
    protected double y;
    int h;
    int w;

    public GameObject (double x, double y, int height, int width){
        this.x = x;
        this.y = y;
        this.h = height;
        this.w = width;
    }
}
