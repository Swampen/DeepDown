package deepDown.gameObjects;

import deepDown.level.Sprite;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public abstract class Pickup extends GameObject{
    private boolean isPickedUp = false;

    /**
     * Constructor.
     * @param x Specified x-coordinate.
     * @param y Specified y-coordinate.
     * @param width Specified width.
     * @param height Specified height.
     * @param sprite Specified {@code Sprite}.
     */
    public Pickup(double x, double y, int width, int height, Sprite sprite) {
        super(x, y, width, height, sprite);
    }

    /**
     * Sets the value of the property isPickedUp.
     * @param pickedUp Defines if the {@code Pickup} is picked up.
     */
    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    /**
     * Gets the value of the property isPickedUp.
     * @return {@code true} if the {@code Pickup} is picked up,
     *         {@code false} by default.
     */
    public boolean isPickedUp() {
        return isPickedUp;
    }
}
