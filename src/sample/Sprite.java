package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Sprite {

    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private int type;

    public Sprite (Image image, GameObject go, int type){
        this.image = image;
        this.positionX = go.getX();
        this.positionY = go.getY();
        this.width = go.getW();
        this.height = go.getH();
        this.type = type;
    }

    public void render (GraphicsContext gc, double sx, double sy){
        gc.drawImage(image, sx, sy, width, height, positionX, positionY, 40, 40);
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX, positionY, width,height);
    }





    public boolean collision(Sprite sprite){

        if( sprite.getBoundary().intersects(this.getBoundary()) && sprite.type == 1){
            System.out.println("wall collision");
        } else if( sprite.getBoundary().intersects(this.getBoundary()) && sprite.type == 2) {
            System.out.println("Coin collision");
        } else if( sprite.getBoundary().intersects(this.getBoundary()) && sprite.type == 3){
            System.out.println("Enemy Collision");
        }

        return sprite.getBoundary().intersects(this.getBoundary());
    }
}