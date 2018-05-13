package deepDown.gameObjects;


import deepDown.level.Sprite;
import javafx.geometry.Rectangle2D;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public abstract class GameObject {
    private double x;
    private double y;
    private final int h;
    private final int w;
    private final Sprite sprite;

    /**
     * Constructor.
     * @param x Specified x-coordinate.
     * @param y Specified y-coordinate.
     * @param width Specified width.
     * @param height Specified height.
     * @param sprite Specified {@code Sprite}.
     */
    public GameObject (double x, double y, int width, int height, Sprite sprite){
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.sprite = sprite;
    }

    /**
     * Gets the value of the property x.
     * @return Returns the {@code GameObject} x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the value of the property y.
     * @return Returns the {@code GameObject} y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the value of the property w.
     * @return Returns the {@code GameObject} width value.
     */
    public int getW() {
        return w;
    }

    /**
     * Gets the value of the property h.
     * @return Returns the {@code GameObject} height value.
     */
    public int getH() {
        return h;
    }

    /**
     * Makes a new x by adding the parameter.
     * @param x to be added to this x value.
     */
    public void setX(double x) {
        this.x += x;
    }

    /**
     * Makes a new y by adding the parameter.
     * @param y to be added to this y value.
     */
    public void setY(double y) {
        this.y += y;
    }

    /**
     * Get the boundary of a {@link Rectangle2D}.
     * @return Returns the boundary's x and y position and it's h and w.
     */
    private Rectangle2D getBoundingBox(){
        return new Rectangle2D(x, y, w, h);
    }

    /**
     * Checks if there is an intersection between this {@code GameObject}.
     * and the specified {@code GameObject}.
     * @param other the specified {@code GameObject}.
     * @return returns true if there is a collision and false if not.
     */
    public boolean isColliding(GameObject other){
        return this.getBoundingBox().intersects(other.getBoundingBox());
    }

    /**
     * Calculates the intersection of this {@code GameObject} with the
     * specified {@code GameObject}. Returns a new {@link Rectangle2D}
     * that represents the intersection of the two rectangles.
     * If the two GameObjects don't intersect, it will return
     * an empty {@link Rectangle2D}.
     * (Similar method with {@link java.awt.Rectangle}'s intersection method).
     * @param     other the specified {@code Rectangle}.
     * @return    the {@code Rectangle2D} that represents the
     *            largest intersection between this {@code GameObject}.
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
     * Gets the sprite for this {@code GameObject}.
     * @return the visual representation of this {@code GameObject}.
     */
    public Sprite getSprite(){
        return sprite;
    }
}