package deepDown.gameObjects;


public class Avatar extends DynamicGameObject {
    private int lives = 3;
    private boolean isAlive;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;

    public Avatar(double x, double y, int height, int width, double xVelo, double yVelo) {
        super(x, y, height, width, xVelo, yVelo);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isAlive() {
        if(lives == 0){
            return !isAlive;
        } else {
            return isAlive;
        }
    }

    public int reduceLives() {
        --lives;
        return lives;
    }

    public void setMovementState(boolean movable){
        this.canMoveUp = movable;
        this.canMoveDown = movable;
        this.canMoveLeft = movable;
        this.canMoveRight = movable;
    }

    public void setCanMoveUp(boolean movable){
        this.canMoveUp = movable;
    }

    public void setCanMoveDown(boolean movable){
        this.canMoveDown = movable;
    }

    public void setCanMoveLeft(boolean movable){
        this.canMoveLeft = movable;
    }

    public void setCanMoveRight (boolean movable){
        this.canMoveRight = movable;
    }

    public boolean getCanMoveUp(){
        return canMoveUp;
    }

    public boolean getCanMoveDown(){
        return canMoveDown;
    }

    public boolean getCanMoveLeft(){
        return canMoveLeft;
    }

    public boolean getCanMoveRight(){
        return canMoveRight;
    }
}