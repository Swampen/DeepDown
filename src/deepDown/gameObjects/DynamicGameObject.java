package deepDown.gameObjects;

public abstract class DynamicGameObject extends GameObject {
    private double xVelo;
    private double yVelo;

    /**
     * Constructor for DynamicGameObject
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param height Specified height
     * @param width Specified width
     * @param xVelo Specified xVelocity
     * @param yVelo Specified yVelocity
     */
    public DynamicGameObject(double x, double y, int height, int width, double xVelo, double yVelo) {
        super(x, y, height, width);
        this.xVelo = xVelo;
        this.yVelo = yVelo;
    }

    /**
     * Sets the xVelocity
     * @param xVelo Value to assign the xVelo to
     */
    public void setXVelo(double xVelo) {
        this.xVelo = xVelo;
    }

    /**
     * Sets the yVelocity
     * @param yVelo Value to assign the yVelo to
     */
    public void setYVelo(double yVelo) {
        this.yVelo = yVelo;
    }

    /**
     * Updates the DynamicGameObject's x and y position using Velocities and time
     * @param time
     */
    public void posUpdate(double time) {
        this.setX(xVelo*time);
        this.setY(yVelo*time);
    }

    /**
     * Reverses the DynamicGameObjects x and y velocities
     */
    public void reverseVelo(){
        this.xVelo = -xVelo;
        this.yVelo = -yVelo;
    }
}
