package deepDown.gameObjects.enemy;

import deepDown.Sprite;
import deepDown.gameObjects.DynamicGameObject;

public class Enemy extends DynamicGameObject {

    /**
     * Constructor for enemy
     * @param x Specified x-coordinate
     * @param y Specified y-coordinate
     * @param width Specified width
     * @param height Specified height
     * @param xVelo Specified xVelocity
     * @param yVelo Specified yVelocity
     * @param sprite Specified sprite
     */
    public Enemy(double x, double y, int width, int height, Sprite sprite, double xVelo, double yVelo) {
        super(x, y, width, height, sprite, xVelo, yVelo);
    }
}
