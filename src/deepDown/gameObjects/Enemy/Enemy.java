package deepDown.gameObjects.Enemy;

import deepDown.gameObjects.DynamicGameObject;
import deepDown.gameObjects.GameObject;

public class Enemy extends DynamicGameObject {

    public Enemy(double x, double y, int height, int width,double xVelo, double yVelo) {
        super(x, y, height, width, xVelo, yVelo);
    }
}
