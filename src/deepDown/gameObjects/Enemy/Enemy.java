package deepDown.gameObjects.Enemy;

import deepDown.gameObjects.DynamicGameObject;

public class Enemy extends DynamicGameObject {

    /**
     * Constructor for Enemy
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param height Specified height
     * @param width Specified width
     * @param xVelo Specified xVelocity
     * @param yVelo Specified yVelocity
     */
    public Enemy(double x, double y, int height, int width,double xVelo, double yVelo) {
        super(x, y, height, width, xVelo, yVelo);
    }
}
