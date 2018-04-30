package deepDown.gameObjects;


import deepDown.Sprite;
import javafx.geometry.Rectangle2D;

public abstract class GameObject {

    /**
     * The GameObjects position, height, width and sprite variables
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
     * @param sprite GameObject's sprite
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
    public Rectangle2D getBoundingBox(){
        return new Rectangle2D(x, y, w, h);
    }

    /**
     * Checks if there is an intersection between this {@code GameObject}
     * and the specified {@code GameObject}.
     * @param other the specified {@code GameObject}
     * @return returns true if there is a collision and false if not
     */
    public boolean isColliding(GameObject other){
        return this.getBoundingBox().intersects(other.getBoundingBox());
    }

    /**
     * Calculates the intersection of this {@code GameObject} with the
     * specified {@code GameObject}. Returns a new {@code Rectangle}
     * that represents the intersection of the two rectangles.
     * If the two GameObjects don't intersect, it will return
     * an empty Rectangle2D
     * @param     other the specified {@code Rectangle}
     * @return    the {@code Rectangle2D} that represents the
     *            largest intersection between this {@code GameObject}
     */
    public Rectangle2D intersection(GameObject other) {
        double goX1 = this.x;
        double goY1 = this.y;
        double goX2 = other.x;
        double goY2 = other.y;
        double goW1 = goX1 + this.w;
        double goH1 = goY1 + this.h;
        double goW2 = goX2 + other.w;
        double goH2 = goY2 + other.h;
        if (goX1 < goX2) goX1 = goX2;
        if (goY1 < goY2) goY1 = goY2;
        if (goW1 > goW2) goW1 = goW2;
        if (goH1 > goH2) goH1 = goH2;
        goW1 -= goX1;
        goH1 -= goY1;

        if (goW1 < Integer.MIN_VALUE) goW1 = Integer.MIN_VALUE;
        if (goH1 < Integer.MIN_VALUE) goH1 = Integer.MIN_VALUE;
        return new Rectangle2D(goX1, goY1, goW1, goH1);
    }

    /**
     * Getter for sprite
     * @return sprite tied to the GameObject
     */
    public Sprite getSprite(){
        return sprite;
    }
}