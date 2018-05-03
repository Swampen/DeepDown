package deepDown.gameObjects;

import deepDown.Sprite;

public class Door extends GameObject {

    private boolean isOpen = false;

    /**
     * Constructor.
     * @param x Specified x-coordinate.
     * @param y Specified y-coordinate.
     * @param width Specified width.
     * @param height Specified height.
     * @param sprite Specified {@code Sprite}.
     */
    public Door(double x, double y, int width, int height, Sprite sprite) {
        super(x, y, width, height, sprite);
    }

    /**
     * Sets the value of the property isOpen.
     * @param open Defines if the {@code Door} is open.
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    /**
     * Gets the value of the property isOpen.
     * @return {@code true} if the {@code Door} is open,
     *         {@code false} by default.
     */
    public boolean isOpen() {
        return isOpen;
    }
}
