package deepDown.gameObjects;


import deepDown.Sprite;

public class Avatar extends DynamicGameObject {
    private boolean isAlive;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;

    public Avatar(double x, double y, int width, int height, Sprite sprite, double xVelo, double yVelo) {
        super(x, y, width, height, sprite, xVelo, yVelo);
    }

    /**
     * Sets whether the avatar can move in any of the cardinal directions
     * @param movable boolean that determines if the avatar can move that direction
     */
    public void setMovementState(boolean movable){
        this.canMoveUp = movable;
        this.canMoveDown = movable;
        this.canMoveLeft = movable;
        this.canMoveRight = movable;
    }

    /**
     * Sets whether the avatar can move up
     * @param movable boolean that determines if the avatar can move up
     */
    public void setCanMoveUp(boolean movable){
        this.canMoveUp = movable;
    }

    /**
     * Sets whether the avatar can move down
     * @param movable boolean that determines if the avatar can move down
     */
    public void setCanMoveDown(boolean movable){
        this.canMoveDown = movable;
    }

    /**
     * Sets whether the avatar can move left
     * @param movable boolean that determines if the avatar can move left
     */
    public void setCanMoveLeft(boolean movable){
        this.canMoveLeft = movable;
    }

    /**
     * Sets whether the avatar can move right
     * @param movable boolean that determines if the avatar can move right
     */
    public void setCanMoveRight (boolean movable){
        this.canMoveRight = movable;
    }

    /**
     * Getter for canMoveUp
     * @return returns the boolean for being able to move up
     */
    public boolean getCanMoveUp(){
        return canMoveUp;
    }

    /**
     * Getter for canMoveDown
     * @return returns the boolean for being able to move down
     */
    public boolean getCanMoveDown(){
        return canMoveDown;
    }

    /**
     * Getter for canMoveLeft
     * @return returns the boolean for being able to move left
     */
    public boolean getCanMoveLeft(){
        return canMoveLeft;
    }

    /**
     * Getter for canMoveRight
     * @return returns the boolean for being able to move right
     */
    public boolean getCanMoveRight(){
        return canMoveRight;
    }
}