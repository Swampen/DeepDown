package deepDown.gameObjects;

import deepDown.Sprite;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public abstract class DynamicGameObject extends GameObject {
    private double xVelo;
    private double yVelo;

    /**
     * Constructor.
     * @param x Specified x-coordinate.
     * @param y Specified y-coordinate.
     * @param width Specified width.
     * @param height Specified height.
     * @param sprite Specified {@code Sprite}.
     * @param xVelo Specified velocity on the X-axis.
     * @param yVelo Specified velocity on the Y-axis.
     */
    public DynamicGameObject(double x, double y, int width, int height, Sprite sprite, double xVelo, double yVelo) {
        super(x, y, width, height, sprite);
        this.xVelo = xVelo;
        this.yVelo = yVelo;
    }

    /**
     * Sets the value of the property xVelo.
     * @param xVelo The velocity the {@code DynamicGameObject} will
     *              have on the X-axis.
     */
    public void setXVelo(double xVelo) {
        this.xVelo = xVelo;
    }

    /**
     * Sets the value of the property yVelo.
     * @param yVelo The velocity the {@code DynamicGameObject} will
     *              have on the Y-axis.
     */
    public void setYVelo(double yVelo) {
        this.yVelo = yVelo;
    }

    /**
     * Updates this {@code DynamicGameObjects}'s x and y position using velocities and time.
     * @param time Defines how much time since last frame.
     */
    public void posUpdate(double time) {
        this.setX(xVelo*time);
        this.setY(yVelo*time);
    }

    /**
     * Reverses this {@code DynamicGameObject}'s velocity on the X- and Y-axis.
     */
    public void reverseVelo(){
        this.xVelo = -xVelo;
        this.yVelo = -yVelo;
    }
}
