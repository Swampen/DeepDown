package deepDown.gameObjects.enemy;

import deepDown.Sprite;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public class HorizontalEnemy extends Enemy {

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
    public HorizontalEnemy(double x, double y, int width, int height, Sprite sprite, double xVelo, double yVelo) {
        super(x, y, width, height, sprite, xVelo, yVelo);
    }
}
