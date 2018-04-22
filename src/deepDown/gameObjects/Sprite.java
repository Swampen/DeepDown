package deepDown.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite{

    private Image image;
    private GameObject go;
    private Type type;
    private int tileX;
    private int tileY;

    /**
     * Constructor for sprite
     * @param image The specified image to use for drawing
     * @param go the specified object to make into a sprite
     * @param type the specified type of object it is
     * @param tileX what x part for the image to use
     * @param tileY what y part of the image to use
     */
    public Sprite (Image image, GameObject go, Type type, int tileX, int tileY){
        this.image = image;
        this.go = go;
        this.type = type;
        this.tileX = tileX;
        this.tileY = tileY;
    }

    /**
     * Draws a sprite onto the specified GraphicsContext
     * @param gc The specified GraphicsContext
     */
    public void render (GraphicsContext gc){
        gc.drawImage(image, tileX, tileY, go.getW(), go.getH(), go.getX(), go.getY(), go.getW(), go.getH());
    }

    /**
     * Change what part of the image to use
     * @param tileX what x part of the image to use
     * @param tileY what y part of the image to use
     */
    public void changeSprite(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public Type getType() {
        return type;
    }

    /**
     * Getter for go
     * @return returns the go
     */
    public GameObject getGo() {
        return this.go;
    }
}