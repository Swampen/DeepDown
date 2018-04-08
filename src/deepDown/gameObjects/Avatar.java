package deepDown.gameObjects;


public class Avatar extends GameObject {
    private int lives;
    private boolean isAlive;
    private double xVelo;
    private double yVelo;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;

    public Avatar(double x, double y, int height, int width, int lives, boolean isAlive, double xVelo, double yVelo) {
        super(x, y, height, width);
        this.lives = lives;
        this.isAlive = isAlive;
        this.xVelo = xVelo;
        this.yVelo = yVelo;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    public void posUpdate() {
        this.setX(xVelo);
        this.setY(yVelo);
    }

    public void setXVelo(double xVelo) {
        this.xVelo = xVelo;
    }

    public void setYVelo(double yVelo) {
        this.yVelo = yVelo;
    }

    public double getXVelo(){
        return xVelo;
    }
    public double getYVelo(){
        return yVelo;
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