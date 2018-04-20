package deepDown.gameObjects;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.awt.*;

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
        return new Rectangle2D(x, y, w, h);
    }

    public boolean isColliding(GameObject other){
        return this.getBoundary().intersects(other.getBoundary());
    }

    /**
     * Computes the intersection of this {@code Rectangle} with the
     * specified {@code Rectangle}. Returns a new {@code Rectangle}
     * that represents the intersection of the two rectangles.
     * If the two rectangles do not intersect, the result will be
     * an empty rectangle.
     *
     * @param     other the specified {@code Rectangle}
     * @return    the largest {@code Rectangle} contained in both the
     *            specified {@code Rectangle} and in
     *            this {@code Rectangle}; or if the rectangles
     *            do not intersect, an empty rectangle.
     */
    public Rectangle2D intersection(GameObject other) {
        double tx1 = this.x;
        double ty1 = this.y;
        double rx1 = other.x;
        double ry1 = other.y;
        double tx2 = tx1; tx2 += this.w;
        double ty2 = ty1; ty2 += this.h;
        double rx2 = rx1; rx2 += other.w;
        double ry2 = ry1; ry2 += other.h;
        if (tx1 < rx1) tx1 = rx1;
        if (ty1 < ry1) ty1 = ry1;
        if (tx2 > rx2) tx2 = rx2;
        if (ty2 > ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will never overflow (they will never be
        // larger than the smallest of the two source w,h)
        // they might underflow, though...
        if (tx2 < Integer.MIN_VALUE) tx2 = Integer.MIN_VALUE;
        if (ty2 < Integer.MIN_VALUE) ty2 = Integer.MIN_VALUE;
        return new Rectangle2D(tx1, ty1, tx2, ty2);
    }
}