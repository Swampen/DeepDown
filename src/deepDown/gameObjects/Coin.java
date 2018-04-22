package deepDown.gameObjects;

public class Coin extends GameObject{

    private boolean isPickedUp;

    /**
     * Constructor for Coin
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param height Specified height
     * @param width Specified width
     * @param isPickedUp if the coin is picked up
     */
    public Coin(double x, double y, int height, int width, boolean isPickedUp) {
        super(x, y, height, width);
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
