package deepDown.gameObjects;

import deepDown.Sprite;

public class Door extends GameObject {

    private boolean isOpen;

    /**
     * Constructor for Door
     * @param x specified x-coordinate
     * @param y specified y-coordinate
     * @param width specified width
     * @param height specified height
     * @param isOpen whether the door is open
     */
    public Door(double x, double y, int width, int height, Sprite sprite, boolean isOpen) {
        super(x, y, width, height, sprite);
        this.isOpen = isOpen;
    }

    /**
     * Getter for the isOpen boolean
     * @return isOpen
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Setter for the isOpen boolean
     * @param open what boolean value to set it to
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
