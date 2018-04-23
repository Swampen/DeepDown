package deepDown.gameObjects;

import deepDown.Sprite;

public class Key extends GameObject {

    private boolean isPickedUp;

    /**
     * Constructor for Key
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param width Specified width
     * @param height Specified height
     * @param isPickedUp if the coin is picked up
     */
    public Key(double x, double y, int width, int height, Sprite sprite, boolean isPickedUp) {
        super(x, y, width, height, sprite);
        this.isPickedUp = isPickedUp;
    }

    /**
     * Getter for the pickedUp boolean
     * @return pickedUp boolean
     */
    public boolean isPickedUp(){
        return isPickedUp;
    }

    /**
     * Setter for the pickedUp boolean
     * @param isPickedUp boolean that determines if the key is picked up
     */
    public void setPickedUp(boolean isPickedUp){
        this.isPickedUp = isPickedUp;
    }
}
