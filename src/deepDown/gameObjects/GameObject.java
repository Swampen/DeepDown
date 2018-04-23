package deepDown.gameObjects;


import deepDown.Sprite;
import javafx.geometry.Rectangle2D;

public abstract class GameObject {

    /**
     * The GameObjects position, height and width variables
     */
    private double x;
    private double y;
    private final int h;
    private final int w;
    private Sprite sprite;

    /**
     * Constructor for the GameObject class
     * @param x GameObject's x-Coordinate
     * @param y GameObject's y-coordinate
     * @param width GameObject's width
     * @param height GameObject's height
     */
    public GameObject (double x, double y, int width, int height, Sprite sprite){
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.sprite = sprite;
    }

    /**
     * @return Returns the GameObjects x value
     */
    public double getX() {
        return x;
    }

    /**
     * @return Returns the GameObjects y value
     */
    public double getY() {
        return y;
    }

    /**
     * @return Returns the GameObjects w value
     */
    public int getW() {
        return w;
    }

    /**
     * @return Returns the GameObjects h value
     */
    public int getH() {
        return h;
    }

    /**
     * Updates the x value
     * @param x add this value to the x value
     */
    public void setX(double x) {
        this.x += x;
    }

    /**
     * Updates the y value
     * @param y add this value to the y value
     */
    public void setY(double y) {
        this.y += y;
    }

    /**
     * Get the boundary of a Rectangle2D
     * @return Returns the boundary's x and y position and it's h and w
     */
    public Rectangle2D getBoundary(){
        return new Rectangle2D(x, y, w, h);
    }

    /**
     * Checks for collision between two boundaries
     * @param other gameObject to check collision for
     * @return returns true if there is a collision and false if not
     */
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

    public Sprite getSprite(){
        return sprite;
    }
}