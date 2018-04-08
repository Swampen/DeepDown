package deepDown.gameObjects;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite{

    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private Type type;
    private int tileX;
    private int tileY;

    public Sprite (Image image, GameObject go, Type type, int tileX, int tileY){
        this.image = image;
        this.positionX = go.getX();
        this.positionY = go.getY();
        this.width = go.getW();
        this.height = go.getH();
        this.type = type;
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public void render (GraphicsContext gc){
        gc.drawImage(image, tileX, tileY, width, height, positionX, positionY, 40, 40);
    }

    public void renderPlayer (GraphicsContext gc){
        gc.drawImage(image, tileX, tileY, width, height, positionX, positionY, 30, 30);
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX, positionY, width,height);
    }





    public boolean collision(Sprite sprite){

       /* if( sprite.getBoundary().intersects(this.getBoundary()) && sprite.type == 1){
            System.out.println("wall collision");
        } else if( sprite.getBoundary().intersects(this.getBoundary()) && sprite.type == 2) {
            System.out.println("Coin collision");
        } else if( sprite.getBoundary().intersects(this.getBoundary()) && sprite.type == 3){
            System.out.println("Enemy Collision");
        }*/

        return sprite.getBoundary().intersects(this.getBoundary());
    }
    public Type getType() {
        return type;
    }
}