package sample;

public class Avatar extends GameObject{
    private int lives;
    private boolean isAlive;
    private double xVelo;
    private double yVelo;

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

    public void addVelocity(double x, double y) {
        xVelo += x;
        yVelo += y;
    }

    public void posUpdate(double time) {
        x += xVelo * time;
        y += yVelo * time;
    }

    public double getyVelo(){
        return yVelo;
    }


}
