package deepDown.gameObjects.Enemy;

import deepDown.gameObjects.Enemy.Enemy;

public class HorizontalEnemy extends Enemy {

    /**
     * Constructor for HorizontalEnemy
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param height Specified height
     * @param width Specified width
     * @param xVelo Specified xVelocity
     * @param yVelo Specified yVelocity
     */
    public HorizontalEnemy(double x, double y, int height, int width, double xVelo, double yVelo) {
        super(x, y, height, width, xVelo, yVelo);
    }
}
