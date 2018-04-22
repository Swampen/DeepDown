package deepDown.gameObjects;

public class Door extends GameObject {

    private boolean isOpen;

    /**
     * Constructor for Door
     * @param x specified x-coordinate
     * @param y specified y-coordinate
     * @param height specified height
     * @param width specified width
     * @param isOpen whether the door is open
     */
    public Door(double x, double y, int height, int width, boolean isOpen) {
        super(x, y, height, width);
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
