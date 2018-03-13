package sample;

public class Avatar extends GameObject{
    private int lives;
    private boolean isAlive;

    public Avatar(double x, double y, int height, int width, int lives, boolean isAlive) {
        super(x, y, height, width);
        this.lives = lives;
        this.isAlive = isAlive;
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


}
