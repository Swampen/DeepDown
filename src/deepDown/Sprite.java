package deepDown;

import deepDown.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Ole-Martin Heggen
 */
public class Sprite{

    private final Image image;
    private int tilesetX;
    private int tilesetY;

    /**
     * Constructor.
     * @param image The specified image to use for drawing.
     * @param tilesetX What x part for the image to use.
     * @param tilesetY What y part of the image to use.
     */
    public Sprite (Image image, int tilesetX, int tilesetY){
        this.image = image;
        this.tilesetX = tilesetX;
        this.tilesetY = tilesetY;
    }

    /**
     * Draws a sprite onto the specified GraphicsContext
     * @param gc The specified {@code GraphicsContext}.
     * @param go The {@code GameObject} to render.
     */
    public void render (GraphicsContext gc, GameObject go){
        gc.drawImage(image, tilesetX, tilesetY, go.getW(), go.getH(), go.getX(), go.getY(), go.getW(), go.getH());
    }

    /**
     * Changes the part of the tileset this {@code Sprite} uses.
     * @param tilesetX What x part of the image to use.
     * @param tilesetY What y part of the image to use.
     */
    public void changeSprite(int tilesetX, int tilesetY) {
        this.tilesetX = tilesetX;
        this.tilesetY = tilesetY;
    }
}