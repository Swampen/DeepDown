package deepDown.gameObjects;

public abstract class DynamicGameObject extends GameObject {
    private double xVelo;
    private double yVelo;

    public DynamicGameObject(double x, double y, int height, int width, double xVelo, double yVelo) {
        super(x, y, height, width);
        this.xVelo = xVelo;
        this.yVelo = yVelo;
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

    //Updates the sprites position
    public void posUpdate(double time) {
        this.setX(xVelo*time);
        this.setY(yVelo*time);
    }
}
