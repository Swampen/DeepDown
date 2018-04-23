package deepDown;

import deepDown.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite{

    private Image image;
    private int tilesetX;
    private int tilesetY;

    /**
     * Constructor for sprite
     * @param image The specified image to use for drawing
     * @param tilesetX what x part for the image to use
     * @param tilesetY what y part of the image to use
     */
    public Sprite (Image image, int tilesetX, int tilesetY){
        this.image = image;
        this.tilesetX = tilesetX;
        this.tilesetY = tilesetY;
    }

    /**
     * Draws a sprite onto the specified GraphicsContext
     * @param gc The specified GraphicsContext
     */
    public void render (GraphicsContext gc, GameObject go){
        gc.drawImage(image, tilesetX, tilesetY, go.getW(), go.getH(), go.getX(), go.getY(), go.getW(), go.getH());
    }

    /**
     * Change what part of the image to use
     * @param tilesetX what x part of the image to use
     * @param tilesetY what y part of the image to use
     */
    public void changeSprite(int tilesetX, int tilesetY) {
        this.tilesetX = tilesetX;
        this.tilesetY = tilesetY;
    }
}