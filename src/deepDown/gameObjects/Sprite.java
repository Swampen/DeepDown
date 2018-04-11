package deepDown.gameObjects;

import javafx.geometry.Rectangle2D;
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
        gc.drawImage(image, tileX, tileY, go.getW(), go.getH(), go.getX(), go.getY(), 40, 40);
    }
    public void renderAvatar (GraphicsContext gc){
        gc.drawImage(image, tileX, tileY, go.getW(), go.getH(), go.getX(), go.getY(), 30, 30);
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(go.getX(), go.getY(), go.getW(), go.getH());
    }

    public boolean collision(Sprite sprite){
        return sprite.getBoundary().intersects(this.getBoundary());
    }

    public Type getType() {
        return type;
    }

    public GameObject getGo() {
        return this.go;
    }
}