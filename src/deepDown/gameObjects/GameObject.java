package deepDown.gameObjects;

import javafx.geometry.Rectangle2D;

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

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public void setX(double x) {
        prevX = this.x;
        this.x += x;
    }

    public void setY(double y) {
        prevY = this.y;
        this.y += y;
    }

    public void revertXPos(){
        this.x = prevX;
    }

    public void revertYPos(){
        this.y = prevY;
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(this.x, this.y, this.w, this.w);
    }

    public boolean collision(GameObject go){
        return go.getBoundary().intersects(this.getBoundary());
    }
}