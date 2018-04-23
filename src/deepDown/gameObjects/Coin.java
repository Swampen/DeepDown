package deepDown.gameObjects;

import deepDown.Sprite;

public class Coin extends GameObject{

    private boolean isPickedUp;

    /**
     * Constructor for Coin
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param width Specified width
     * @param height Specified height
     * @param isPickedUp if the coin is picked up
     * @param sprite Specified sprite
     */
    public Coin(double x, double y, int width, int height, Sprite sprite, boolean isPickedUp) {
        super(x, y, width, height, sprite);
        this.isPickedUp = isPickedUp;
    }

    /**
     * Setter for the pickedUp boolean
     * @param pickedUp boolean what determines if the coin is picked up
     */
    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    /**
     * Getter for the pickedUp boolean
     * @return pickedUp boolean
     */
    public boolean isPickedUp() {
        return isPickedUp;
    }
}
