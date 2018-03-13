package sample;

public abstract class GameObject {
    private double x;
    private double y;
    int h;
    int w;

    public GameObject (double x, double y, int height, int width){
        this.x = x;
        this.y = y;
        this.h = height;
        this.w = width;
    }
}
