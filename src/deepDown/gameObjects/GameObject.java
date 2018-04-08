package deepDown.gameObjects;

public abstract class GameObject {
    private double x;
    private double y;
    private int h;
    private int w;
    private double prevX;
    private double prevY;

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
        setPrevX(this.x);
        this.x += x;
    }

    public void setY(double y) {
        setPrevY(this.y);
        this.y += y;
    }

    public void setPrevX(double x) {
        this.prevX = x;
    }

    public void setPrevY(double y) {
        this.prevY = y;
    }

    public double getPrevX(){
        return prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    public void setXPos(double x){
        this.x = prevX;
    }

    public void setYPos(double y){
        this.y = prevY;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}