package deepDown.gameObjects;


import deepDown.Sprite;

public class Avatar extends DynamicGameObject {
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;

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
    public Avatar(double x, double y, int width, int height, Sprite sprite, double xVelo, double yVelo) {
        super(x, y, width, height, sprite, xVelo, yVelo);
    }

    /**
     * Sets the value of the property canMoveUp, canMoveDown, canMoveLeft and canMoveRight.
     * @param movable Defines if the {@code Avatar} can move at all.
     */
    public void setMovementState(boolean movable){
        this.canMoveUp = movable;
        this.canMoveDown = movable;
        this.canMoveLeft = movable;
        this.canMoveRight = movable;
    }

    /**
     * Sets the value of the property canMoveUp.
     * @param movable Defines if the {@code Avatar} can move up.
     */
    public void setCanMoveUp(boolean movable){
        this.canMoveUp = movable;
    }

    /**
     * Sets the value of the property canMoveDown.
     * @param movable Defines if the {@code Avatar} can move down.
     */
    public void setCanMoveDown(boolean movable){
        this.canMoveDown = movable;
    }

    /**
     * Sets the value of the property canMoveLeft,
     * @param movable Defines if the {@code Avatar} can move left.
     */
    public void setCanMoveLeft(boolean movable){
        this.canMoveLeft = movable;
    }

    /**
     * Sets the value of the property canMoveRight.
     * @param movable Defines if the {@code Avatar} can move right.
     */
    public void setCanMoveRight (boolean movable){
        this.canMoveRight = movable;
    }

    /**
     * Gets the value of the property canMoveUp.
     * @return {@code true} if the {@code Avatar} can move up, {@code false} otherwise.
     */
    public boolean getCanMoveUp(){
        return canMoveUp;
    }

    /**
     * Gets the value of the property canMoveDown.
     * @return {@code true} if the {@code Avatar} can move down, {@code false} otherwise.
     */
    public boolean getCanMoveDown(){
        return canMoveDown;
    }

    /**
     * Gets the value of the property canMoveLeft.
     * @return {@code true} if the {@code Avatar} can move left, {@code false} otherwise.
     */
    public boolean getCanMoveLeft(){
        return canMoveLeft;
    }

    /**
     * Gets the value of the property canMoveRight.
     * @return {@code true} if the {@code Avatar} can move right, {@code false} otherwise.
     * */
    public boolean getCanMoveRight(){
        return canMoveRight;
    }
}