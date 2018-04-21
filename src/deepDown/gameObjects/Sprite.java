package deepDown.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite{

    private Image image;
    private GameObject go;
    private Type type;
    private int tileX;
    private int tileY;

    public Sprite (Image image, GameObject go, Type type, int tileX, int tileY){
        this.image = image;
        this.go = go;
        this.type = type;
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public void render (GraphicsContext gc){
        gc.drawImage(image, tileX, tileY, go.getW(), go.getH(), go.getX(), go.getY(), go.getW(), go.getH());
    }

    public void changeSprite(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public Type getType() {
        return type;
    }

    public GameObject getGo() {
        return this.go;
    }
}